package com.mozhimen.basick.utilk.view.display

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowManager
import com.mozhimen.basick.utilk.content.UtilKApplication

/**
 * @ClassName KDisplayUtil
 * @Description TODO
 * @Author Kolin Zhao / Mozhimen
 * @Date 2021/12/24 14:27
 * @Version 1.0
 */
object UtilKDisplay {
    private val _context = UtilKApplication.instance.get()

    /**
     * dp转px
     * @param dp Float
     * @return Int
     */
    @JvmStatic
    fun dp2px(dp: Float): Int =
        if (dp <= 0f) 0
        else (dp * (_context.resources.displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).toInt()

    /**
     * dp转px
     * @param dp Float
     * @return Int
     */
    @JvmStatic
    fun dp2px2(dp: Float): Int =
        if (dp <= 0f) 0
        else (dp * _context.resources.displayMetrics.density).toInt()

    /**
     * px转dp
     * @param px Int
     * @return Float
     */
    @JvmStatic
    fun px2dp(px: Float): Int =
        if (px <= 0f) 0
        else (px / (_context.resources.displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT)).toInt()

    /**
     * px转dp
     * @param px Float
     * @return Int
     */
    @JvmStatic
    fun px2dp2(px: Float): Int =
        if (px <= 0f) 0
        else (px / _context.resources.displayMetrics.density).toInt()

    /**
     * px转sp
     * @param px Float
     * @return Float
     */
    @JvmStatic
    fun px2sp(px: Float): Int =
        if (px <= 0f) 0
        else (px / _context.resources.displayMetrics.scaledDensity + 0.5f).toInt()

    /**
     * sp转px
     * @param sp Float
     * @return Int
     */
    @JvmStatic
    fun sp2px(sp: Float): Int =
        if (sp <= 0f) 0
        else TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, _context.resources?.displayMetrics).toInt()


    /**
     * getDisplayMetrics
     * @return DisplayMetrics
     */
    @JvmStatic
    fun getDisplayMetrics(): DisplayMetrics {
        return _context.resources.displayMetrics
    }

    /**
     * getDisplayMetrics
     * @return DisplayMetrics
     */
    @JvmStatic
    fun getDisplayMetrics2(): DisplayMetrics {
        val displayMetrics = DisplayMetrics()
        (_context.getSystemService(Context.WINDOW_SERVICE) as? WindowManager? ?: return displayMetrics).apply {
            defaultDisplay.getMetrics(displayMetrics)
        }
        return displayMetrics
    }
}