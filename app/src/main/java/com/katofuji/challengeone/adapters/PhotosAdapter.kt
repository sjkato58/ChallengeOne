package com.katofuji.challengeone.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.katofuji.challengeone.components.data.PhotoItem
import com.katofuji.challengeone.viewholders.PhotoViewHolder

class PhotosAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>()
{
    var mDataList = emptyList<PhotoItem>()

    fun setData(photoItemList: List<PhotoItem>)
    {
        val photoDiffUtil = PhotosDiffUtil(mDataList, photoItemList)
        val photoDiffResult = DiffUtil.calculateDiff(photoDiffUtil)
        this.mDataList = photoItemList
        photoDiffResult.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int
    {
        return mDataList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    {
        return PhotoViewHolder.createHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int)
    {
        if (holder is PhotoViewHolder)
        {
            val item = mDataList[position]
            holder.onBindDataToVH(item)
        }
    }

    override fun onViewRecycled(holder: RecyclerView.ViewHolder)
    {
        super.onViewRecycled(holder)
        if (holder is PhotoViewHolder)
        {
            holder.onHolderRecycled()
        }
    }
}