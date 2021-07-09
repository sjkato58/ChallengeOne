package com.katofuji.challengeone.components.downloads

import android.app.Application
import android.text.TextUtils
import com.android.volley.VolleyError
import com.katofuji.challengeone.R
import com.katofuji.challengeone.components.data.PhotoItem
import com.katofuji.challengeone.components.dataparsers.PhotoItemParser
import org.json.JSONArray

class PhotoItemDownloadHelper(
    application: Application
): DownloadHelper<List<PhotoItem>>(application)
{





    override fun onDownloadSucceeded(
        resultString: String,
        downloadListener: DownloadListener<List<PhotoItem>>
    )
    {
        if (!TextUtils.isEmpty(resultString))
        {
            val jsonResult = JSONArray(resultString)
            val photoParser = PhotoItemParser()
            val contentList = photoParser.parsePhotoItemList(jsonResult)
            if (contentList != null && contentList.isNotEmpty())
            {
                onParsingSuccess(contentList, downloadListener)
                return
            }
        }
        onDownloadErrored(VolleyError("Parsing Error"), downloadListener)
    }

    override fun getUrl(): String
    {
        return mApplication.resources.getString(R.string.url_photolist)
    }

    override fun getTag(): String
    {
        return mApplication.resources.getString(R.string.tag_photoitems)
    }
}