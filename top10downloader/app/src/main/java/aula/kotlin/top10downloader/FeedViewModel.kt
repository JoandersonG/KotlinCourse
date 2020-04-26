package aula.kotlin.top10downloader

//import androidx.activity.viewModels
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.security.KeyStore
import java.util.*

private const val TAG = "FeedViewModel"

val EMPTY_FEED_LIST: List<FeedEntry> = Collections.emptyList()

class FeedViewModel : ViewModel(),  DownloadData.DownloaderCallBack {

    private var downloadData : DownloadData? = null
    private var feedCachedUrl = "INVALIDATED"

    private val feed = MutableLiveData<List<FeedEntry>>()
    val feedEntries: LiveData<List<FeedEntry>>
        get() = feed

    init {
        feed.postValue(EMPTY_FEED_LIST)
    }

    fun downloadUrl (feedUrl : String) {
        Log.d(TAG, "downloadUrl called")
        if (feedUrl != feedCachedUrl) {
            Log.d(TAG,"downloadUrl starting async task")
            downloadData = DownloadData(this)
            downloadData?.execute(feedUrl)
            feedCachedUrl = feedUrl
            Log.d(TAG, "downloadUrl done")
        } else {
            Log.d(TAG,"downloadUrl: url not  changed")
        }

    }

    fun invalidate() {
        feedCachedUrl = "INVALIDATE"
    }

    override fun onDataAvaliable(data: List<FeedEntry>) {
        Log.d(TAG, "on dataAvaliable called")
        feed.value = data
    }

    override fun onCleared() {
        Log.d(TAG, "onCleared: cancelling pending download")
        downloadData?.cancel(true)
    }
}