package com.katofuji.challengeone.components.downloads

import android.app.Application
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest

/**
 * Download class created to act as a foundation for future download systems. Functions
 * for mostly GET requests, so other download types would require a different approach.
 * Custom downloads require overriding the 'onDownloadSucceeded', 'getUrl' and 'getTag'
 * methods.
 */
abstract class DownloadHelper<T>(val mApplication: Application)
{
    init
    {
        RequestQueueSingleton.getInstance(mApplication)
    }

    fun onDownloadFeed(downloadListener: DownloadListener<T>)
    {
        val url = getUrl()

        val listener = Response.Listener<String> {
            onDownloadSucceeded(it, downloadListener)
        }
        val errorListener = Response.ErrorListener {
            onDownloadErrored(it, downloadListener)
        }
        //Log.i("AppCore", "url: $url")
        val stringRequest = StringRequest(Request.Method.GET, url, listener, errorListener)
        stringRequest.tag = getTag()

        RequestQueueSingleton.getInstance(mApplication).addToRequestQueue(stringRequest)
    }

    /**
     * Method used to handle parsing downloaded data.
     */
    abstract fun onDownloadSucceeded(resultString: String, downloadListener: DownloadListener<T>)

    abstract fun getUrl(): String

    abstract fun getTag():String

    fun onParsingSuccess(data: T, downloadListener: DownloadListener<T>)
    {
        downloadListener.onSuccess(data)
    }

    fun onDownloadErrored(volleyError: VolleyError, downloadListener: DownloadListener<T>)
    {
        downloadListener.onFailure(volleyError)
    }

    fun onCancelDownloads()
    {
        RequestQueueSingleton.getInstance(mApplication).cancelDownloadRequest(getTag())
    }

    interface DownloadListener<T> {
        fun onSuccess(data: T)
        fun onFailure(volleyError: VolleyError)
    }
}