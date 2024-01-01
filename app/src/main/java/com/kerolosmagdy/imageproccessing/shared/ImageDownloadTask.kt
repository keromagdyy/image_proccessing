package com.kerolosmagdy.imageproccessing.shared

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class ImageDownloadTask(private val callback: (Bitmap?) -> Unit) :
    AsyncTask<String, Void, Bitmap?>() {

    override fun doInBackground(vararg params: String?): Bitmap? {
        val imageUrl = params[0]
        var bitmap: Bitmap? = null
        try {
            val url = URL(imageUrl)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            bitmap = BitmapFactory.decodeStream(input)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmap
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        callback.invoke(result)
    }
}

fun convertImageUrlToBitmap(imageUrl: String, callback: (Bitmap?) -> Unit) {
    ImageDownloadTask(callback).execute(imageUrl)
}
