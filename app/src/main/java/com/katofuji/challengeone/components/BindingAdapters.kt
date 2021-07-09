package com.katofuji.challengeone.components

import android.net.Uri
import android.util.Log
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.katofuji.challengeone.R
import com.katofuji.challengeone.components.data.PhotoItem
import com.katofuji.challengeone.utils.COConsts
import com.katofuji.challengeone.utils.COUtils
import com.katofuji.challengeone.widgets.GlideApp

class BindingAdapters
{
    /**********************
     * ViewHolder Methods *
     **********************/

    companion object
    {
        /**
         * Method used to load Images from feed.
         * NB: currently, unable to load images using glide, unless swapping out Header -> User-Agent.
         * Uncertain why, but problems.
         * Found solution thanks to:
         * https://stackoverflow.com/questions/66284817/glide-unable-to-load-url-images-of-this-type
         */
        @BindingAdapter("android:setimagefromurl")
        @JvmStatic
        fun setImageFromUrl(imageView: ImageView, url: String?)
        {
            url?.let {
                //Need this User-Agent change in order for image to load.  If do not use, Glide responds with HTTP GONE error (file not found)
                val headers = LazyHeaders.Builder().addHeader("User-Agent", COConsts.glideUA).build()
                val glideUrl = GlideUrl(url, headers)
                val requestOptions = RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                GlideApp.with(imageView.context)
                    .load(glideUrl)
                    .placeholder(R.drawable.bkgd_curved_grey)
                    .apply(requestOptions)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView)
            }
        }

        @BindingAdapter("android:onphotoitemclicked")
        @JvmStatic
        fun onPhotoItemClicked(view: ConstraintLayout, photoItem: PhotoItem?)
        {
            view.setOnClickListener {
                if (photoItem != null)
                {
                    val message = "onPhotoItemClicked: ${photoItem.title} & url: ${photoItem.url}"
                    COUtils.displayToast(view.context, message, false)
                }
            }
        }


    }
}