package com.parthdesai1208.triviaapp.model.room

import android.content.Context
import androidx.room.*

@Database(
    entities = [DataEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getUserDao(): TriviaDao

    companion object {

        @Volatile
        private var instance: AppDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance
            ?: synchronized(LOCK) {
            instance
                ?: buildDatabase(
                    context
                ).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "MyDatabase.db"
            ).fallbackToDestructiveMigration().build()
        //fallbackToDestructiveMigration() help to migrate from one version to another
    }
}