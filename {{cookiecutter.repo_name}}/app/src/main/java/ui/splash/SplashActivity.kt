package {{ cookiecutter.package_name }}.ui.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
{% if cookiecutter.login == "y" %}
import {{ cookiecutter.package_name }}.ui.login.LoginActivity
{% endif %}
{% if cookiecutter.login == "n" %}
import {{ cookiecutter.package_name }}.ui.main.MainActivity
{% endif %}

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        {% if cookiecutter.login == "y" %}
        startActivity(Intent(this, LoginActivity::class.java))
        {% endif %}
        {% if cookiecutter.login == "n" %}
        startActivity(Intent(this, MainActivity::class.java))
        {% endif %}
        finish()
    }
}