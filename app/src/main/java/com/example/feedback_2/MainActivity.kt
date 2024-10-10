package com.example.feedback_2

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var buttonAddBook: Button
    private lateinit var buttonSyncData: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var novelViewModel: NovelViewModel
    private lateinit var novelAdapter: NovelAdapter
    private lateinit var syncReceiver: BroadcastReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonAddBook = findViewById(R.id.buttonAddBook)
        buttonSyncData = findViewById(R.id.buttonSyncData)
        recyclerView = findViewById(R.id.recyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        novelAdapter = NovelAdapter()
        recyclerView.adapter = novelAdapter

        novelViewModel = ViewModelProvider(this).get(NovelViewModel::class.java)

        novelViewModel.getAllNovels().observe(this, Observer<List<Novel>> { novels ->
            novelAdapter.setNovels(novels)
        })


        buttonAddBook.setOnClickListener {
            val intent = Intent(this, AddNovelActivity::class.java)
            startActivity(intent)
        }

        buttonSyncData.setOnClickListener {

            val syncDataTask = SyncDataTask(this)
            syncDataTask.execute()
        }

        syncReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context, intent: Intent) {
                Toast.makeText(context, "Datos sincronizados", Toast.LENGTH_SHORT).show()

                // Actualizar la lista de novelas después de la sincronización
                novelViewModel.getAllNovels().observe(this@MainActivity, Observer<List<Novel>> { novels ->
                    novelAdapter.setNovels(novels)
                })
            }
        }

        registerReceiver(syncReceiver, IntentFilter("com.example.novelmanager.SYNC_COMPLETE"))
    }

    private fun NovelAdapter(): NovelAdapter {
        return NovelAdapter(
            onItemClickListener = { novel -> // Implementar la lógica para mostrar detalles de la novela
            },
            onFavoriteClickListener = { novel ->
                // Implementar la lógica para marcar/desmarcar como favorito
            },
            onDeleteClickListener = { novel ->
                // Implementar la lógica para eliminar la novela
            }
        )

    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(syncReceiver)
    }
}
