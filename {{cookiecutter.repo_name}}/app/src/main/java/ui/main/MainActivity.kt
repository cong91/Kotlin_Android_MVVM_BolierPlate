package {{ cookiecutter.package_name }}.ui.main

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import {{ cookiecutter.package_name }}.R
import {{ cookiecutter.package_name }}.core.BaseActivity
import {{ cookiecutter.package_name }}.databinding.ActivityMainBinding
{% if cookiecutter.retrofit == "y" %}
import {{ cookiecutter.package_name }}.ui.list.PostListActivity
{% endif %}

class MainActivity : BaseActivity<MainActivityViewModel, ActivityMainBinding>(MainActivityViewModel::class.java) {
    override fun getLayoutRes() = R.layout.activity_main
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
		{% if cookiecutter.retrofit == "y" %}
        showListActivity()
     	{% endif %}
    }
    {% if cookiecutter.retrofit == "y" %}
    fun showListActivity(){
        var button = Button(this)
        button.text = "Posts List"
        button.setOnClickListener { v ->
            run {
                var intent = Intent(v.context, PostListActivity::class.java)
                startActivity(intent)
            }
        }
        binding.rootView.addView(button)
    }
    {% endif %}
}
