package com.katofuji.challengeone.viewholders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.katofuji.challengeone.R
import com.katofuji.challengeone.components.data.PhotoItem
import com.katofuji.challengeone.databinding.ItemPhotoBinding

class PhotoViewHolder(
    private val mBinding: ItemPhotoBinding
): RecyclerView.ViewHolder(mBinding.root)
{
    companion object
    {
        fun createHolder(parent: ViewGroup): PhotoViewHolder
        {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemPhotoBinding.inflate(inflater, parent, false)
            return PhotoViewHolder(binding)
        }
    }

    fun onBindDataToVH(photoItem: PhotoItem)
    {
        mBinding.photoItem = photoItem
        mBinding.executePendingBindings()
    }

    fun onHolderRecycled()
    {
        mBinding.imageView.setImageResource(R.drawable.bkgd_curved_grey)
        mBinding.textView.text = ""
    }
}