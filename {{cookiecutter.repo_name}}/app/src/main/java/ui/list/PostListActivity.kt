package {{ cookiecutter.package_name }}.ui.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import {{ cookiecutter.package_name }}.R
import {{ cookiecutter.package_name }}.core.BaseActivity
import {{ cookiecutter.package_name }}.core.api.Resource
import {{ cookiecutter.package_name }}.core.api.Status
import {{ cookiecutter.package_name }}.core.recyclerview.SingleTypeAdapter
import {{ cookiecutter.package_name }}.databinding.ActivityPostListBinding
import {{ cookiecutter.package_name }}.db.entities.Post
import {{ cookiecutter.package_name }}.utils.extensions.logI

class PostListActivity : BaseActivity<PostListViewModel, ActivityPostListBinding>(PostListViewModel::class.java) {
    override fun getLayoutRes(): Int {
        return R.layout.activity_post_list
    }
    private lateinit var observerPost: Observer<Resource<List<Post>>>
    private var errorSnackbar: Snackbar? = null
    private var postsAdapter: SingleTypeAdapter<Post>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.errorMessage.observe(this, Observer { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

        postsAdapter = SingleTypeAdapter(applicationContext, R.layout.item_post)
        binding.postList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.postList.adapter = postsAdapter

        observerPost = Observer { postsResource ->
            when(postsResource!!.status){
                Status.LOADING -> viewModel.onRetrievePostListStart()
                Status.ERROR -> {
                    viewModel.onRetrievePostListFinish()
                }
                Status.SUCCESS -> {
                    viewModel.onRetrievePostListFinish()
                    if (postsResource.data != null) postsAdapter!!.addAll(postsResource.data)
                }
            }
        }
        viewModel.loadPosts().observe(this,observerPost)
    }

    private fun showError(@StringRes errorMessage: Int) {
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError() {
        errorSnackbar?.dismiss()
    }
}