package {{ cookiecutter.package_name }}.di.module

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import {{ cookiecutter.package_name }}.core.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
{% if cookiecutter.retrofit == "y" %}
import {{ cookiecutter.package_name }}.ui.list.PostListViewModel
{% endif %}
@Module
abstract class ViewModelModule {

	{% if cookiecutter.retrofit == "y" %}
    @Binds
    @IntoMap
    @ViewModelKey( PostListViewModel::class )
    // Bind your View Model here
    abstract fun bindPostListViewModel( mainViewModel: PostListViewModel ): ViewModel
    {% endif %}
    @Binds
    abstract fun bindViewModelFactory( factory: ViewModelFactory):
            ViewModelProvider.Factory

}