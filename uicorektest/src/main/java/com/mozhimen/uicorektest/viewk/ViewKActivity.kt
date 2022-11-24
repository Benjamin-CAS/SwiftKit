package com.mozhimen.uicorektest.viewk

import android.view.View
import com.mozhimen.basick.elemk.activity.bases.BaseActivityVB
import com.mozhimen.basick.utilk.exts.start
import com.mozhimen.uicorektest.databinding.ActivityViewkBinding

class ViewKActivity : BaseActivityVB<ActivityViewkBinding>() {

    fun goViewKSteps(view: View) {
        start<ViewKStepsActivity>()
    }

    fun goViewKViews(view: View) {
        start<ViewKViewsActivity>()
    }
}