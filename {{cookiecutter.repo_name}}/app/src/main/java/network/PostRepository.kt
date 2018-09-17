package {{ cookiecutter.package_name }}.network

import android.app.Application
import android.arch.lifecycle.LiveData
import android.content.Context
import {{ cookiecutter.package_name }}.core.api.ApiResponse
import {{ cookiecutter.package_name }}.core.api.NetworkBoundResource
import {{ cookiecutter.package_name }}.core.api.RateLimiter
import {{ cookiecutter.package_name }}.core.api.Resource
import {{ cookiecutter.package_name }}.db.AppDatabase
import {{ cookiecutter.package_name }}.db.entities.Post
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class PostRepository  @Inject constructor(private val postAPI : PostApi,private val appDB : AppDatabase) {

    val repoRateLimiter = RateLimiter<String>(10, TimeUnit.MINUTES)

    fun fetchPost(): LiveData<Resource<List<Post>>> {
        return object : NetworkBoundResource<List<Post>, Resource<List<Post>>>() {
            override fun saveCallResult(item: Resource<List<Post>>) {
                appDB.postDao().insertAll(item.data!!)
            }

            override fun shouldFetch(data: List<Post>?): Boolean = repoRateLimiter.shouldFetch("posts")
            override fun loadFromDb(): LiveData<List<Post>> {
                return appDB.postDao().getAllPosts()
            }

            override fun createCall(): LiveData<ApiResponse<Resource<List<Post>>>> {
                return postAPI.getPosts()
            }

            override fun onFetchFailed() {
                repoRateLimiter.reset("posts")
            }
        }.asLiveData()
    }
}