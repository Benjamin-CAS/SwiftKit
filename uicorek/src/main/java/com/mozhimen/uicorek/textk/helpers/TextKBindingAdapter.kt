package com.mozhimen.uicorek.textk.helpers

import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.databinding.BindingAdapter

/**
 * @ClassName TextKBindingAdapter
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/5/26 16:39
 * @Version 1.0
 */
object TextKBindingAdapter {
    @JvmStatic
    @BindingAdapter(value = ["loadTextColorWhen", "loadTextColorWhen_statusTrue", "loadTextColorWhen_statusFalse"], requireAll = true)
    fun loadTextColorWhen(textView: TextView, boolean: Boolean, @ColorInt loadTextColorWhen_statusTrue: Int, @ColorInt loadTextColorWhen_statusFalse: Int) {
        if (boolean) {
            textView.setTextColor(loadTextColorWhen_statusTrue)
        } else {
            textView.setTextColor(loadTextColorWhen_statusFalse)
        }
    }
}