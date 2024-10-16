package com.example.feedback_2


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Novel::class], version = 1)
abstract class NovelDatabase : RoomDatabase() {

    abstract fun novelDao(): NovelDao

    companion object {
        @Volatile
        private var instance: NovelDatabase? = null

        fun getInstance(context: Context): NovelDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    NovelDatabase::class.java,
                    "novel_database"
                ).fallbackToDestructiveMigration().build().also { instance = it }
            }
        }
    }
}
