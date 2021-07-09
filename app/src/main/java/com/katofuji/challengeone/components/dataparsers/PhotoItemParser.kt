package com.katofuji.challengeone.components.dataparsers

import com.katofuji.challengeone.components.data.PhotoItem
import org.json.JSONArray
import org.json.JSONObject

class PhotoItemParser
{

    fun parsePhotoItemList(resultJson: JSONArray?): List<PhotoItem>?
    {
        if (resultJson != null)
        {
            val list = ArrayList<PhotoItem>()
            val listL1 = resultJson.length()
            var photoItem: PhotoItem? = null
            for (i in 0 until listL1)
            {
                photoItem = parsePhotoItem(resultJson.optJSONObject(i))
                if (photoItem != null)
                {
                    list.add(photoItem)
                }
            }
            if (list.isNotEmpty())
            {
                return list
            }
        }
        return null
    }

    fun parsePhotoItem(resultJson: JSONObject?): PhotoItem?
    {
        if (resultJson != null)
        {
            val photoItem = PhotoItem()
            photoItem.albumId = resultJson.optInt("albumId", -1)
            photoItem.id = resultJson.optInt("id", -1)
            photoItem.title = resultJson.optString("title", "")
            photoItem.url = resultJson.optString("url", "")
            photoItem.thumbnailUrl = resultJson.optString("thumbnailUrl", "")

            return photoItem
        }
        return null
    }
}