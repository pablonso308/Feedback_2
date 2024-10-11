package com.example.feedback_2

import android.app.Application
import androidx.lifecycle.LiveData
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NovelRepository(application: Application) {
    private val novelDao: NovelDao
    val allNovels: LiveData<List<Novel>>
    private val executorService: ExecutorService

    init {
        val database = NovelDatabase.getInstance(application)
        novelDao = database.novelDao()
        allNovels = novelDao.getAllNovels()
        executorService = Executors.newFixedThreadPool(2)
    }

    companion object {
        fun getAllNovels(): Any {
            return NovelDao.getAllNovels()
        }
    }

    fun insert(novel: Novel) {
        executorService.execute {
            novelDao.insert(novel)
        }
    }
}


