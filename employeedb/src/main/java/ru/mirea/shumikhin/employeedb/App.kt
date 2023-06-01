package ru.mirea.shumikhin.employeedb

import android.app.Application
import android.content.Context
import androidx.room.Room

class App: Application() {

    private var database: AppDatabase? = null

    override fun onCreate() {
        super.onCreate()
        database = createDatabase(this)
    }

    fun createDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database.db"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    fun getDatabase(): AppDatabase? {
        return database
    }
}