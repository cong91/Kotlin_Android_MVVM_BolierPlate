package {{ cookiecutter.package_name }}.ui.list

import android.app.Application
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer
import android.view.View
import {{ cookiecutter.package_name }}.App
import {{ cookiecutter.package_name }}.R
import {{ cookiecutter.package_name }}.core.BaseViewModel
import {{ cookiecutter.package_name }}.db.AppDatabase
import {{ cookiecutter.package_name }}.db.entities.Post
import {{ cookiecutter.package_name }}.network.PostApi
import {{ cookiecutter.package_name }}.utils.extensions.logI
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import {{ cookiecutter.package_name }}.core.api.Resource
import {{ cookiecutter.package_name }}.core.api.Status
import {{ cookiecutter.package_name }}.network.PostRepository
import javax.inject.Inject

class PostListViewModel  @Inject constructor(app: Application) : BaseViewModel(app){
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }
    var posts: MutableLiveData<List<Post>> = MutableLiveData()
    @Inject
    lateinit var postRepository: PostRepository
    init {
        (app as? App)?.component?.inject(this)
    }


    override fun onCleared() {
        super.onCleared()
    }

    fun loadPosts() : LiveData<Resource<List<Post>>> {
        return postRepository.fetchPost()
    }

    fun onRetrievePostListStart(){
        loadingVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    fun onRetrievePostListFinish(){
        loadingVisibility.value = View.GONE
    }



    private fun onRetrievePostListError(error: Throwable) {
        error.printStackTrace()
        errorMessage.value = R.string.post_error
    }
}