package {{ cookiecutter.package_name }}.ui.login

import android.app.Application
import {{ cookiecutter.package_name }}.App
import {{ cookiecutter.package_name }}.core.BaseViewModel
import {{ cookiecutter.package_name }}.core.recyclerview.BaseViewAdapter
import javax.inject.Inject

class RegisterViewModel @Inject constructor(app : Application) : BaseViewModel(app) {
    init {
        (app as? App)?.component?.inject(this)
    }
}