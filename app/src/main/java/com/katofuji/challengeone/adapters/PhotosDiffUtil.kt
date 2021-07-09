package com.katofuji.challengeone.adapters

import androidx.recyclerview.widget.DiffUtil
import com.katofuji.challengeone.components.data.PhotoItem

class PhotosDiffUtil(
    private val oldList: List<PhotoItem>,
    private val newList: List<PhotoItem>
): DiffUtil.Callback()
{
    override fun getOldListSize(): Int
    {
        return oldList.size
    }

    override fun getNewListSize(): Int
    {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
    {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean
    {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return oldItem.id == newItem.id
                && oldItem.albumId == newItem.albumId
                && oldItem.title == newItem.title
                && oldItem.url == newItem.url
                && oldItem.thumbnailUrl == newItem.thumbnailUrl
    }
}