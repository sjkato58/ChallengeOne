package com.katofuji.challengeone.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import androidx.appcompat.widget.AppCompatImageView

/**
 * Class used to draw ImageView content as rounded.
 */
class RoundedImageView : AppCompatImageView
{
    var mRadius: Float = 8.0f

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init
    {
        try
        {
            val density = context.resources.displayMetrics.density
            mRadius = 8f * density
        }
        catch (ex: java.lang.Exception)
        {
            ex.printStackTrace()
        }
    }

    override fun onDraw(canvas: Canvas?)
    {
        try
        {
            val clipPath = Path()
            val rect = RectF(0f, 0f, this.width.toFloat(), this.height.toFloat())
            clipPath.addRoundRect(rect, mRadius, mRadius, Path.Direction.CW)
            canvas?.clipPath(clipPath)
        }
        catch (ex: Exception)
        {
            ex.printStackTrace()
        }
        super.onDraw(canvas)
    }
}