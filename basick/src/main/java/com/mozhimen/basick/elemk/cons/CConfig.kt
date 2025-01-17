package com.mozhimen.basick.elemk.cons

import android.content.res.Configuration
import androidx.annotation.RequiresApi

/**
 * @ClassName CConfig
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/6/30 16:31
 * @Version 1.0
 */
object CConfig {
    object UiMode {
        const val TYPE_MASK = Configuration.UI_MODE_TYPE_MASK
        const val TYPE_UNDEFINED = Configuration.UI_MODE_TYPE_UNDEFINED
        const val TYPE_NORMAL = Configuration.UI_MODE_TYPE_NORMAL
        const val TYPE_DESK = Configuration.UI_MODE_TYPE_DESK
        const val TYPE_CAR = Configuration.UI_MODE_TYPE_CAR
        const val TYPE_TELEVISION = Configuration.UI_MODE_TYPE_TELEVISION
        const val TYPE_APPLIANCE = Configuration.UI_MODE_TYPE_APPLIANCE
        const val TYPE_WATCH = Configuration.UI_MODE_TYPE_WATCH

        @RequiresApi(CVersCode.V_26_8_O)
        const val TYPE_VR_HEADSET = Configuration.UI_MODE_TYPE_VR_HEADSET
        const val NIGHT_MASK = Configuration.UI_MODE_NIGHT_MASK
        const val NIGHT_UNDEFINED = Configuration.UI_MODE_NIGHT_UNDEFINED
        const val NIGHT_NO = Configuration.UI_MODE_NIGHT_NO
        const val NIGHT_YES = Configuration.UI_MODE_NIGHT_YES
    }

    object ScreenLayout {
        const val SIZE_MASK = Configuration.SCREENLAYOUT_SIZE_MASK
        const val SIZE_UNDEFINED = Configuration.SCREENLAYOUT_SIZE_UNDEFINED
        const val SIZE_SMALL = Configuration.SCREENLAYOUT_SIZE_SMALL
        const val SIZE_NORMAL = Configuration.SCREENLAYOUT_SIZE_NORMAL
        const val SIZE_LARGE = Configuration.SCREENLAYOUT_SIZE_LARGE

        const val SIZE_XLARGE = Configuration.SCREENLAYOUT_SIZE_XLARGE
        const val LONG_MASK = Configuration.SCREENLAYOUT_LONG_MASK
        const val LONG_UNDEFINED = Configuration.SCREENLAYOUT_LONG_UNDEFINED
        const val LONG_NO = Configuration.SCREENLAYOUT_LONG_NO
        const val LONG_YES = Configuration.SCREENLAYOUT_LONG_YES

        const val LAYOUTDIR_MASK = Configuration.SCREENLAYOUT_LAYOUTDIR_MASK
        const val LAYOUTDIR_SHIFT = Configuration.SCREENLAYOUT_LAYOUTDIR_SHIFT
        const val LAYOUTDIR_UNDEFINED = Configuration.SCREENLAYOUT_LAYOUTDIR_UNDEFINED
        const val LAYOUTDIR_LTR = Configuration.SCREENLAYOUT_LAYOUTDIR_LTR
        const val LAYOUTDIR_RTL = Configuration.SCREENLAYOUT_LAYOUTDIR_RTL

        @RequiresApi(CVersCode.V_23_6_M)
        const val ROUND_MASK = Configuration.SCREENLAYOUT_ROUND_MASK

        @RequiresApi(CVersCode.V_23_6_M)
        const val ROUND_UNDEFINED = Configuration.SCREENLAYOUT_ROUND_UNDEFINED

        @RequiresApi(CVersCode.V_23_6_M)
        const val ROUND_NO = Configuration.SCREENLAYOUT_ROUND_NO

        @RequiresApi(CVersCode.V_23_6_M)
        const val ROUND_YES = Configuration.SCREENLAYOUT_ROUND_YES
        const val UNDEFINED = Configuration.SCREENLAYOUT_UNDEFINED
    }

    object Orientation {
        const val UNDEFINED = Configuration.ORIENTATION_UNDEFINED
        const val PORTRAIT = Configuration.ORIENTATION_PORTRAIT
        const val LANDSCAPE = Configuration.ORIENTATION_LANDSCAPE
        const val SQUARE = Configuration.ORIENTATION_SQUARE
    }
}