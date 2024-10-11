package com.example.feedback_2

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface NovelDao {


    @Query("SELECT * FROM novel_table ORDER BY title ASC")
    fun getAllNovels(): LiveData<List<Novel>>
    abstract fun insert(novel: Novel)

    companion object {
        fun getAllNovels() {
            return NovelDao.getAllNovels()

        }
    }


}


