package com.mozhimen.underlayktest

import android.os.Bundle
import android.view.View
import com.mozhimen.basick.basek.BaseKActivityVB
import com.mozhimen.basick.extsk.start
import com.mozhimen.underlayk.logk.LogK
import com.mozhimen.underlayktest.databinding.ActivityUnderlaykBinding
import com.mozhimen.underlayktest.logk.LogKActivity

class UnderlayKActivity : BaseKActivityVB<ActivityUnderlaykBinding>() {
    override fun initData(savedInstanceState: Bundle?) {
        vb.underlaykPrintLog.setOnClickListener { LogK.w("这是一个测试") }
    }

    fun goLogK(view: View) {
        start<LogKActivity>()
    }
}