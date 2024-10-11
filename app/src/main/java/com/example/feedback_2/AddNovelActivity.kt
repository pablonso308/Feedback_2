package com.example.feedback_2

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddNovelActivity : AppCompatActivity() {

    private lateinit var editTextTitle: EditText
    private lateinit var editTextAuthor: EditText
    private lateinit var editTextYear: EditText
    private lateinit var editTextSynopsis: EditText
    private lateinit var buttonSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_novel)

        editTextTitle = findViewById(R.id.editTextTitle)
        editTextAuthor = findViewById(R.id.editTextAuthor)
        editTextYear = findViewById(R.id.editTextYear)
        editTextSynopsis = findViewById(R.id.editTextSynopsis)
        buttonSave = findViewById(R.id.buttonSave)

        buttonSave.setOnClickListener {
            val title = editTextTitle.text.toString()
            val author = editTextAuthor.text.toString()
            val year = editTextYear.text.toString().toInt()
            val synopsis = editTextSynopsis.text.toString()

            val novel = Novel(title = title, author = author, year = year, synopsis = synopsis, isFavorite = false)
            val novelViewModel = ViewModelProvider(this).get(NovelViewModel::class.java)
            novelViewModel.insert(novel)

            finish()
        }
    }
}