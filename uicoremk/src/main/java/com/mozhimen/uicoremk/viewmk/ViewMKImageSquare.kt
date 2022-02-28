package com.mozhimen.uicoremk.viewmk

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

/**
 * @ClassName SquareImageView
 * @Description TODO
 * @Author Kolin Zhao
 * @Date 2021/6/21 15:21
 * @Version 1.0
 */
class ViewMKImageSquare : AppCompatImageView {
    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {}

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val mHeightMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY)
        super.onMeasure(widthMeasureSpec, mHeightMeasureSpec)
    }
}