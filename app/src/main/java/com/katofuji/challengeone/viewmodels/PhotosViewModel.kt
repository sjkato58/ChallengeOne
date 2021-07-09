package com.katofuji.challengeone.viewmodels

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.volley.VolleyError
import com.katofuji.challengeone.components.data.DownloadItem
import com.katofuji.challengeone.components.data.PhotoItem
import com.katofuji.challengeone.components.downloads.DownloadHelper
import com.katofuji.challengeone.components.downloads.PhotoItemDownloadHelper
import kotlinx.coroutines.launch

class PhotosViewModel(application: Application) : AndroidViewModel(application)
{
    private var mPhotosData = MutableLiveData<DownloadItem<List<PhotoItem>>>()
    private val mPhotoItemDownloadHelper = PhotoItemDownloadHelper(application)


    fun loadPhotosData()
    {
        mPhotosData.postValue(DownloadItem.loading(null))
        viewModelScope.launch {
            val downloadListener = object : DownloadHelper.DownloadListener<List<PhotoItem>>
            {
                override fun onSuccess(data: List<PhotoItem>)
                {
                    mPhotosData.postValue(DownloadItem.success(data))
                }

                override fun onFailure(volleyError: VolleyError)
                {
                    var message = "Something went wrong"
                    if (!TextUtils.isEmpty(volleyError.message))
                    {
                        message = volleyError.message.toString()
                    }
                    mPhotosData.postValue(DownloadItem.error(message, null))
                }
            }
            mPhotoItemDownloadHelper.onDownloadFeed(downloadListener)
        }
    }

    fun getPhotosData(): LiveData<DownloadItem<List<PhotoItem>>>
    {
        return mPhotosData
    }

    override fun onCleared()
    {
        super.onCleared()
        mPhotoItemDownloadHelper.onCancelDownloads()
    }
}