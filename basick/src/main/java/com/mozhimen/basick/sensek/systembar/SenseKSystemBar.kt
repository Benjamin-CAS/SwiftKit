package com.mozhimen.basick.sensek.systembar

import android.app.Activity
import com.mozhimen.basick.sensek.systembar.annors.ASenseKSystemBarProperty
import com.mozhimen.basick.sensek.systembar.annors.ASenseKSystemBarPropertyAnd
import com.mozhimen.basick.sensek.systembar.annors.ASenseKSystemBarPropertyOr
import com.mozhimen.basick.sensek.systembar.cons.CProperty
import com.mozhimen.basick.sensek.systembar.helpers.SenseKSystemBarHelper
import com.mozhimen.basick.sensek.systembar.mos.MPropertyConfig
import com.mozhimen.basick.utilk.android.app.getAnnotation
import com.mozhimen.basick.utilk.android.content.UtilKConfiguration
import com.mozhimen.basick.utilk.android.util.dt
import com.mozhimen.basick.utilk.bases.BaseUtilK
import com.mozhimen.basick.utilk.kotlin.getByteStr
import com.mozhimen.basick.utilk.kotlin.toBoolean

/**
 * @ClassName StatusBarK
 * @Description TODO
 * @Author mozhimen
 * @Date 2021/4/14 17:09
 * @Version 1.0
 */
/**
 * 作用: 状态栏管理
 * 用法:
 * //简单用法, 直接使用预制的属性
 * //@ASenseKSystemBarProperty(CProperty.IMMERSED_HARD_STICKY)
 * //高级用法自己组合
 * @ASenseKSystemBarProperty(CProperty.NORMAL)
 * @ASenseKSystemBarPropertyOr(CPropertyOr.IMMERSED_OPEN, CPropertyOr.HIDE_STATUS_BAR, CPropertyOr.HIDE_NAVIGATION_BAR)
 * class SystemBarActivity : BaseActivityVB<ActivitySensekSystembarBinding>() {
 *     override fun initFlag() {
 *         initSenseKSystemBar()
 *     }
 * }
 */
fun Activity.initSenseKSystemBar() {
    SenseKSystemBar.initAnnor(this)
}

fun Activity.initSenseKSystemBar(byteInt: Int) {
    SenseKSystemBar.init(this)
}

object SenseKSystemBar : BaseUtilK() {

    @JvmStatic
    internal fun initAnnor(activity: Activity) {
        var aSenseKSystemBarPropertyInt: Int = (activity.getAnnotation(ASenseKSystemBarProperty::class.java) ?: ASenseKSystemBarProperty(CProperty.NORMAL)).property
        val aSenseKSystemBarPropertyOrs: IntArray? = activity.getAnnotation(ASenseKSystemBarPropertyOr::class.java)?.propertyOr
        val aSenseKSystemBarPropertyAnds: IntArray? = activity.getAnnotation(ASenseKSystemBarPropertyAnd::class.java)?.propertyAnd
        if (aSenseKSystemBarPropertyOrs != null && aSenseKSystemBarPropertyOrs.isNotEmpty()) {
            aSenseKSystemBarPropertyOrs.forEach {
                aSenseKSystemBarPropertyInt = aSenseKSystemBarPropertyInt or it
            }
        }
        if (aSenseKSystemBarPropertyAnds != null && aSenseKSystemBarPropertyAnds.isNotEmpty()) {
            aSenseKSystemBarPropertyAnds.forEach {
                aSenseKSystemBarPropertyInt = aSenseKSystemBarPropertyInt and it
            }
        }
        init(activity, aSenseKSystemBarPropertyInt)
    }

    @JvmStatic
    fun init(activity: Activity, byteInt: Int) {
        init(activity, getConfigForByteInt(byteInt))
    }

    @JvmStatic
    fun init(activity: Activity, mPropertyConfig: MPropertyConfig) {
        init(
            activity,
            mPropertyConfig.isImmersed,
            mPropertyConfig.isImmersedHard,
            mPropertyConfig.isImmersedSticky,
            mPropertyConfig.isHideStatusBar,
            mPropertyConfig.isHideNavigationBar,
            mPropertyConfig.isHideTitleBar,
            mPropertyConfig.isHideActionBar,
            mPropertyConfig.isOverlayStatusBar,
            mPropertyConfig.isOverlayNavigationBar,
            mPropertyConfig.isLayoutStable,
            mPropertyConfig.isFitsSystemWindows,
            mPropertyConfig.isStatusBarBgTranslucent,
            mPropertyConfig.isStatusBarIconLowProfile,
            mPropertyConfig.isThemeCustom,
            mPropertyConfig.isThemeDark
        )
    }

    @JvmStatic
    fun init(
        activity: Activity,
        isImmersed: Boolean = false,
        isImmersedHard: Boolean = false,
        isImmersedSticky: Boolean = false,
        //////////////////////////////////////
        isHideStatusBar: Boolean = false,
        isHideNavigationBar: Boolean = false,
        isHideTitleBar: Boolean = false,
        isHideActionBar: Boolean = false,
        //////////////////////////////////////
        isOverlayStatusBar: Boolean = false,
        isOverlayNavigationBar: Boolean = false,
        isLayoutStable: Boolean = false,//设置布局不受系统栏出现隐藏的而改变位置
        isFitsSystemWindows: Boolean = false,//设置系统栏在控件上方的时候,不遮挡控件
        //////////////////////////////////////
        isStatusBarBgTranslucent: Boolean = false,
        isStatusBarIconLowProfile: Boolean = false,
        isThemeCustom: Boolean = false,
        isThemeDark: Boolean = false
    ) {
        SenseKSystemBarHelper.setSystemBarProperty(activity, isStatusBarBgTranslucent, isStatusBarIconLowProfile)
        SenseKSystemBarHelper.setSystemBarTheme(activity, isThemeCustom, isThemeDark)
        SenseKSystemBarHelper.hideSystemBar(activity, isHideStatusBar, isHideNavigationBar, isHideTitleBar, isHideActionBar)
        SenseKSystemBarHelper.overlaySystemBar(activity, isOverlayStatusBar, isOverlayNavigationBar)
        SenseKSystemBarHelper.setImmersed(activity, isImmersed && isImmersedHard, isImmersed && isImmersedSticky)
        SenseKSystemBarHelper.setLayoutProperty(activity, isLayoutStable, isFitsSystemWindows)
    }

    private fun getConfigForByteInt(byteInt: Int): MPropertyConfig {
        val mPropertyConfig = MPropertyConfig()
        val byteStr = byteInt.getByteStr(16)
        var byteBoolean: Boolean
        byteStr.forEachIndexed { position, c ->
            byteBoolean = c.digitToInt().toBoolean()
            when (position) {
                1 -> mPropertyConfig.isImmersed = byteBoolean
                2 -> mPropertyConfig.isImmersedHard = byteBoolean
                3 -> mPropertyConfig.isImmersedSticky = byteBoolean
                ////////////////////////////////////////////
                4 -> mPropertyConfig.isHideStatusBar = byteBoolean
                5 -> mPropertyConfig.isHideNavigationBar = byteBoolean
                6 -> mPropertyConfig.isHideTitleBar = byteBoolean
                7 -> mPropertyConfig.isHideActionBar = byteBoolean
                ////////////////////////////////////////////
                8 -> mPropertyConfig.isOverlayStatusBar = byteBoolean
                9 -> mPropertyConfig.isOverlayNavigationBar = byteBoolean
                10 -> mPropertyConfig.isLayoutStable = byteBoolean
                11 -> mPropertyConfig.isFitsSystemWindows = byteBoolean
                ////////////////////////////////////////////
                12 -> mPropertyConfig.isStatusBarBgTranslucent = byteBoolean
                13 -> mPropertyConfig.isStatusBarIconLowProfile = byteBoolean
                14 -> mPropertyConfig.isThemeCustom = byteBoolean
                15 -> mPropertyConfig.isThemeDark = byteBoolean
            }
        }
        return mPropertyConfig.also { "getConfigForByteInt mPropertyConfig $it".dt(TAG) }
    }


    @JvmStatic
    internal fun init(activity: Activity) {
//        val statusBarAnnor: ASenseKSystemBarProperty =
//            activity.getAnnotation(ASenseKSystemBarProperty::class.java) ?: ASenseKSystemBarProperty(property = CSystemBarType.NORMAL)
//        val systemBarType = statusBarAnnor.property
//        val isFollowSystem = statusBarAnnor.isFollowSystem
//        val isFontIconDark = statusBarAnnor.isFontIconDark
//        val bgColorLight = statusBarAnnor.bgColorLight
//        val bgColorDark = statusBarAnnor.bgColorDark
//
//        when (statusBarAnnor.property) {
//            CSystemBarType.NORMAL -> {
//
//            }
//
//            CSystemBarType.LOW_PROFILE -> {
//            }
//
//            CSystemBarType.IMMERSED_LIGHT -> {
//            }
//
//            CSystemBarType.IMMERSED_FORCE -> {
//            }
//
//            CSystemBarType.IMMERSED_STICKY -> {
//            }
//
//            CSystemBarType.EXPAND_STATUS_BAR -> {
//
//            }
//
//            CSystemBarType.EXPAND_NAVIGATION_BAR -> {
//
//            }
//
//            CSystemBarType.EXPAND_ALL -> {
//
//            }

//            ASenseKSystemBarType.FULL_SCREEN -> UtilKSystemBar.setImmersed(activity)//设置状态栏全屏
//            ASenseKSystemBarType.IMMERSED -> {
//                UtilKStatusBar.setImmersed(activity)//设置状态栏沉浸式
//                UtilKStatusBarFontIcon.setStatusBarFontIcon(activity, if (statusBarAnnor.isFollowSystem) UtilKUiMode.isOSLightMode() else statusBarAnnor.isFontIconDark)//设置状态栏文字和Icon是否为深色
//            }
//
//            else -> {
//                val statusBarColor = if (statusBarAnnor.isFollowSystem) {
//                    if (UtilKUiMode.isOSLightMode()) statusBarAnnor.bgColorLight else statusBarAnnor.bgColorDark
//                } else {
//                    if (statusBarAnnor.isFontIconDark) statusBarAnnor.bgColorLight else statusBarAnnor.bgColorDark
//                }
//                UtilKStatusBar.setStatusBarColor(activity, UtilKRes.getColor(statusBarColor))
//                UtilKStatusBarFontIcon.setStatusBarFontIcon(activity, if (statusBarAnnor.isFollowSystem) UtilKUiMode.isOSLightMode() else statusBarAnnor.isFontIconDark)//设置状态栏文字和Icon是否为深色
//            }
    }
}