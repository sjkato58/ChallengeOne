package com.katofuji.challengeone.components

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.katofuji.challengeone.R

class VerticalSpacingItemDecoration(
    context: Context
): RecyclerView.ItemDecoration()
{
    val mHeightDidiver: Int = context.resources.getDimensionPixelSize(R.dimen.rv_divider_height)

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    )
    {
        super.getItemOffsets(outRect, view, parent, state)
        val adapterPosition = parent.getChildAdapterPosition(view)
        if (adapterPosition == 0)
        {
            outRect.top = mHeightDidiver
        }
        else
        {
            outRect.top = 0
        }
        outRect.bottom = mHeightDidiver
    }
}