package {{ cookiecutter.package_name }}.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import {{ cookiecutter.package_name }}.db.dao.ExampleDao
import {{ cookiecutter.package_name }}.db.entities.Example
{% if cookiecutter.retrofit == "y" %}
import {{ cookiecutter.package_name }}.db.entities.Post
import {{ cookiecutter.package_name }}.db.dao.PostDao
{% endif %}
{% if cookiecutter.retrofit == "y" %}
@Database(entities = arrayOf(Example::class, Post::class), version = 1)
{% endif %}
{% if cookiecutter.retrofit == "n" %}
@Database(entities = arrayOf(Example::class), version = 1)
{% endif %}
abstract class AppDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao
    {% if cookiecutter.retrofit == "y" %}
    abstract fun postDao(): PostDao
    {% endif %}
}