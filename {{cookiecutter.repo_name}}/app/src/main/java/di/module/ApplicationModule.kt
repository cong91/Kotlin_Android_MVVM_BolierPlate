package {{ cookiecutter.package_name }}.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import {{ cookiecutter.package_name }}.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
{% if cookiecutter.retrofit == "y" %}
import {{ cookiecutter.package_name }}.db.AppDatabase
import {{ cookiecutter.package_name }}.network.PostApi
import {{ cookiecutter.package_name }}.network.PostRepository
{% endif %}
@Module
class ApplicationModule(var app: App) {


    @Provides
    @Singleton
    fun provideApp(): App = app

    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(app)
{% if cookiecutter.retrofit == "y" %}
    @Provides
    @Singleton
    fun providePostRepository(postAPI : PostApi, appDB : AppDatabase): PostRepository {
        return PostRepository(postAPI,appDB)
    }
{% endif %}
}
