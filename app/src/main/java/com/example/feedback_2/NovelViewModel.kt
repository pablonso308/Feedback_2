package com.example.feedback_2



import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class NovelViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NovelRepository = NovelRepository(application)
    val allNovels: LiveData<List<Novel>>

    init {
        allNovels = repository.allNovels
    }

    fun insert(novel: Novel) {
        repository.insert(novel)
    }

    fun delete(novel: Novel) {
        repository.delete(novel)
    }

    fun getAllNovels(): Any {
        return allNovels

    }
}

