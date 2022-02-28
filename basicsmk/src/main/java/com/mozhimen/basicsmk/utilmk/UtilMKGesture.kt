package com.mozhimen.basicsmk.utilmk

import android.view.MotionEvent
import kotlin.math.sqrt

/**
 * @ClassName UtilMKGesture
 * @Description TODO
 * @Author Kolin Zhao / Mozhimen
 * @Date 2022/2/28 14:05
 * @Version 1.0
 */
class UtilMKGesture {
    /**
     * 计算手指间距
     */
    fun fingerDistance(event: MotionEvent): Float {
        val x = event.getX(0) - event.getX(1)
        val y = event.getY(0) - event.getY(1)
        return sqrt((x * x + y * y).toDouble()).toFloat()
    }
}