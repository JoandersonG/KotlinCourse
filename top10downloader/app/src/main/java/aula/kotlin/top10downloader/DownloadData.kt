package aula.kotlin.top10downloader

import android.content.Context
import android.os.AsyncTask
import android.util.Log
import java.io.IOException
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import kotlin.properties.Delegates

private const val TAG = "DonwloadData"

class DownloadData (private val callback: DownloaderCallBack) : AsyncTask<String, Void, String>() {

    interface DownloaderCallBack  {
        fun onDataAvaliable(data: List<FeedEntry>)
    }

    override fun onPostExecute(result: String) {
        val parseApplications = ParseApplications()
        if (result.isNotEmpty()) {
            parseApplications.parse(result)
        }

        callback.onDataAvaliable(parseApplications.applications)
    }

    override fun doInBackground(vararg url: String?): String {
        Log.d(TAG, "doInBackground: starts with ${url[0]}")
        val rssFeed = downloadXML(url[0])
        if (rssFeed.isEmpty()) {
            Log.e(TAG, "doInBackground: error downloading")
        }
        return rssFeed
    }

    private fun downloadXML (urlPath : String?) : String {
        val xmlResult = StringBuilder()

        try {
            val url = URL(urlPath)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

            connection.inputStream.buffered().reader().use {
                xmlResult.append(it.readText())
            }


            Log.d(TAG, "Received ${xmlResult.length} bytes")
            return xmlResult.toString()

        } catch (e: Exception) {
            val errorMessage = when (e) {
                is MalformedURLException -> "downloadXML: invalid URL ${e.message}"
                is IOException -> "downloadXML: IO exception reading data: ${e.message}"
                is SecurityException -> "downloadXML: Secirity exception. Needs permissions? ${e.message}"
                else -> "downloadXML: unknown error : ${e.message}"
            }
            Log.e(TAG,errorMessage)
        }
        return ""

//        try {
//            return URL(urlPath).readText()
//        } catch (e: java.lang.Exception) {
//            val errorMessage = when (e) {
//                is MalformedURLException -> "downloadXML: invalid URL ${e.message}"
//                is IOException -> "downloadXML: IO exception reading data: ${e.message}"
//                is SecurityException -> "downloadXML: Secirity exception. Needs permissions? ${e.message}"
//                else -> "downloadXML: unknown error : ${e.message}"
//            }
//            Log.e(TAG,errorMessage)
//        }
    }
}