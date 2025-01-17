package com.mozhimen.basick.utilk.android.widget

import android.graphics.Typeface
import android.widget.TextView
import androidx.annotation.IntRange
import com.mozhimen.basick.elemk.commons.IA_Listener
import com.mozhimen.basick.utilk.android.content.UtilKContext
import com.mozhimen.basick.utilk.kotlin.toStringTrim

/**
 * @ClassName UtilKViewText
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Date 2022/11/6 0:27
 * @Version 1.0
 */
fun TextView.applyTextStyle(@IntRange(from = 0, to = 3) style: Int = Typeface.NORMAL) {
    UtilKTextView.applyTextStyle(this, style)
}

fun TextView.applyIconFont(iconFont: String = "icons/iconfont.ttf") {
    UtilKTextView.setIconFont(this, iconFont)
}

val TextView.value: String
    get() = UtilKTextView.getValue(this)

fun TextView.getValueIfNotEmpty(invoke: IA_Listener<String>/*(value: String) -> Unit*/) {
    UtilKTextView.getValueIfNotEmpty(this, invoke)
}

fun TextView.applyValueIfNotEmpty(str: String?) {
    UtilKTextView.setValueIfNotEmpty(this, str)
}

object UtilKTextView {
    /**
     * 设置字体的细或粗
     * @param textView TextView
     * @param style Int
     */
    @JvmStatic
    fun applyTextStyle(
        textView: TextView,
        @IntRange(from = 0, to = 3) style: Int = Typeface.NORMAL
    ) {
        textView.typeface = Typeface.defaultFromStyle(style)
    }

    /**
     * 设置字体
     * @param textView TextView
     * @param fontPathWithName String
     */
    @JvmStatic
    fun setIconFont(
        textView: TextView,
        fontPathWithName: String = "fonts/iconfont.ttf"
    ) {
        textView.typeface = Typeface.createFromAsset(UtilKContext.getAssets(textView.context), fontPathWithName)
    }

    @JvmStatic
    fun getValue(textView: TextView): String =
        textView.text.toStringTrim()

    @JvmStatic
    fun getValueIfNotEmpty(textView: TextView, invoke: IA_Listener<String>/*(value: String) -> Unit*/) {
        val value = getValue(textView)
        if (value.isNotEmpty()) invoke.invoke(value)
    }

    @JvmStatic
    fun setValueIfNotEmpty(textView: TextView, str: String?): Boolean {
        return if (!str.isNullOrEmpty()) {
            textView.text = str
            true
        } else false
    }
}