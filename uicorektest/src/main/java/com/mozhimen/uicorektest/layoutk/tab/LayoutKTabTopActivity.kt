package com.mozhimen.uicorektest.layoutk.tab

import android.os.Bundle
import com.mozhimen.basick.basek.BaseKActivityVB
import com.mozhimen.basick.utilk.UtilKRes
import com.mozhimen.uicorek.layoutk.tab.top.mos.MTabTop
import com.mozhimen.uicorektest.R
import com.mozhimen.uicorektest.databinding.ActivityLayoutkTabTopBinding

class LayoutKTabTopActivity : BaseKActivityVB<ActivityLayoutkTabTopBinding>() {
    override fun initData(savedInstanceState: Bundle?) {
        initTabTop()
    }

    private val _tabInfo1 by lazy {
        MTabTop(
            "home",
            UtilKRes.getColor(R.color.white),
            UtilKRes.getColor(R.color.blue_light)
        )
    }

    private val _tabInfo2 by lazy {
        MTabTop(
            "more",
            R.mipmap.layoutk_tab_bottom_layout_fire,
            R.mipmap.layoutk_tab_bottom_layout_fire,
            UtilKRes.getColor(R.color.blue_theme)
        )
    }

    private val _tabInfo3 by lazy {
        MTabTop(
            "mine",
            R.mipmap.layoutk_tab_bottom_layout_fire,
            R.mipmap.layoutk_tab_bottom_layout_fire,
            UtilKRes.getColor(R.color.white),
            UtilKRes.getColor(R.color.blue_light)
        )
    }

    private fun initTabTop() {
        vb.layoutkTabTop.setTabInfo(_tabInfo1)
        vb.layoutkTabTop2.setTabInfo(_tabInfo2)
        vb.layoutkTabTop3.setTabInfo(_tabInfo3)
    }
}