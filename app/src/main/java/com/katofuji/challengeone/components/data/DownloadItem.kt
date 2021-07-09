package com.katofuji.challengeone.components.data

import com.katofuji.challengeone.components.downloads.DownloadStatus

data class DownloadItem<out T>(
    val status: DownloadStatus,
    val data: T?,
    val message: String?
)
{
    companion object
    {
        fun <T> loading(data: T?): DownloadItem<T>
        {
            return DownloadItem(DownloadStatus.Loading, data, null)
        }

        fun <T> success(data: T?): DownloadItem<T>
        {
            return DownloadItem(DownloadStatus.Success, data, null)
        }

        fun <T> error(message: String, data: T?): DownloadItem<T>
        {
            return DownloadItem(DownloadStatus.Error, null, message)
        }
    }
}