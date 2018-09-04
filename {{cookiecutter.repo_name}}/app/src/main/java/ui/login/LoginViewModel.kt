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
    @Inject
    lateinit var postApi: PostApi
    init {
        (app as? App)?.component?.inject(this)
    }
}