package com.example.feedback_2

import android.content.Context
import android.content.Intent
import android.os.AsyncTask

class SyncDataTask(private val context: Context) : AsyncTask<Void, Void, Void>() {

    override fun doInBackground(vararg params: Void?): Void? {
        // Implementar la lógica para sincronizar datos con el servidor remoto
        return null
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)
        // Enviar un broadcast para notificar la finalización de la sincronización
        val intent = Intent("com.example.novelmanager.SYNC_COMPLETE")
        context.sendBroadcast(intent)
    }
}
