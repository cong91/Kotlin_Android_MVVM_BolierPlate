package {{ cookiecutter.package_name }}.network

import android.arch.lifecycle.LiveData
import {{ cookiecutter.package_name }}.db.entities.Post
import io.reactivex.Observable
import retrofit2.http.GET
import {{ cookiecutter.package_name }}.core.api.ApiResponse
import {{ cookiecutter.package_name }}.core.api.Resource

/**
 * The interface which provides methods to get result of webservices
 */
interface PostApi {
    /**
     * Get the list of the pots from the API
     */
    @GET("/bins/chc9c")
    fun getPosts(): LiveData<ApiResponse<Resource<List<Post>>>>
}