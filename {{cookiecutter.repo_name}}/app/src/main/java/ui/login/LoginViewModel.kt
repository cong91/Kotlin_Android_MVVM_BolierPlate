package {{ cookiecutter.package_name }}.ui.login

import android.app.Application
import {{ cookiecutter.package_name }}.App
import {{ cookiecutter.package_name }}.core.BaseViewModel
import {{ cookiecutter.package_name }}.db.AppDatabase
import {{ cookiecutter.package_name }}.network.PostApi
import javax.inject.Inject

class LoginViewModel  @Inject constructor(app: Application) : BaseViewModel(app) {
    @Inject
    lateinit var db: AppDatabase
    {% if cookiecutter.retrofit == "y" %}
    @Inject
    lateinit var postApi: PostApi
    {% endif %}
    init {
        (app as? App)?.component?.inject(this)
    }
}
