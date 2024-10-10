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


    fun getAllNovels(): Any {
        return NovelRepository.getAllNovels() // Asegúrate de que esto retorne un LiveData


    }
}

