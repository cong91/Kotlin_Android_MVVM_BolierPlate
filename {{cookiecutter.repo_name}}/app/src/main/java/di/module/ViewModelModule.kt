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
{% if cookiecutter.login == "y" %}
import {{ cookiecutter.package_name }}.ui.login.LoginViewModel
import {{ cookiecutter.package_name }}.ui.login.RegisterViewModel
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
    {% if cookiecutter.login == "y" %}
    @Binds
    @IntoMap
    @ViewModelKey( LoginViewModel::class )
    // Bind your View Model here
    abstract fun bindLoginActivityViewModel( loginViewModel: LoginViewModel ): ViewModel
    @Binds
    @IntoMap
    @ViewModelKey( RegisterViewModel::class )
    // Bind your View Model here
    abstract fun bindRegisterFragmentViewModel( registerFragmentViewModel: RegisterViewModel): ViewModel
    {% endif %}


    @Binds
    abstract fun bindViewModelFactory( factory: ViewModelFactory):
            ViewModelProvider.Factory

}