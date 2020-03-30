package aula.kotlin.top10downloader

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL

class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG,"OnCreate called")
        val downloadData = DownloadData()
        //downloadData.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml")
        downloadData.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml")
        Log.d(TAG, "onCreate done")

    }

    companion object {
        private class DownloadData : AsyncTask<String, Void, String>() {
            private val TAG = "DonwloadData"
            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                Log.d(TAG, "onPostExecute: parameter is $result")
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
                    val response = connection.responseCode
                    Log.d(TAG, "downloadXML: the response code was $response")

                    val reader = BufferedReader(InputStreamReader(connection.inputStream))

                    val inputBuffer = CharArray(500)
                    var charsRead = 0
                    while (charsRead >= 0) {
                        charsRead = reader.read(inputBuffer)
                        if (charsRead > 0) {
                            xmlResult.append(String(inputBuffer, 0, charsRead))
                        }
                    }
                    reader.close()

//                    connection.inputStream.buffered().reader().use {
//                        xmlResult.append(it.readText())
//                    }


                    Log.d(TAG, "Received ${xmlResult.length} bytes")
                    return xmlResult.toString()

//                } catch (e: MalformedURLException) {
//                    Log.e(TAG, "downloadXML: invalid URL ${e.message}")
//                } catch (e: IOException) {
//                    Log.e(TAG, "downloadXML: IO exception reading data: ${e.message}")
//                } catch (e: SecurityException) {
//                    Log.e(TAG, "downloadXML: Secirity exception. Needs permissions? ${e.message}")
//                } catch (e : Exception) {
//                    Log.e(TAG, "downloadXML: unknown error : ${e.message}")
//                }
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
            }
        }
    }


}
