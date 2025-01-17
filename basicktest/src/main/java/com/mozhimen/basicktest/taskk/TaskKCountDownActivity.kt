package com.mozhimen.basicktest.taskk

import android.os.Bundle
import com.mozhimen.basick.elemk.androidx.appcompat.bases.BaseActivityVB
import com.mozhimen.basick.lintk.optin.annors.ALintKOptIn_ApiInit_ByLazy
import com.mozhimen.basick.lintk.optin.annors.ALintKOptIn_ApiCall_BindLifecycle
import com.mozhimen.basick.taskk.temps.ITaskKCountDownListener
import com.mozhimen.basick.taskk.temps.TaskKCountDown
import com.mozhimen.basicktest.databinding.ActivityTaskkCountDownBinding
import kotlin.math.roundToInt

class TaskKCountDownActivity : BaseActivityVB<ActivityTaskkCountDownBinding>() {

    @OptIn(ALintKOptIn_ApiInit_ByLazy::class, ALintKOptIn_ApiCall_BindLifecycle::class)
    private val _taskKCountDown: TaskKCountDown by lazy { TaskKCountDown().apply { bindLifecycle(this@TaskKCountDownActivity) } }

    @OptIn(ALintKOptIn_ApiInit_ByLazy::class, ALintKOptIn_ApiCall_BindLifecycle::class)
    override fun initView(savedInstanceState: Bundle?) {
        _taskKCountDown.start(10000, object : ITaskKCountDownListener {
            override fun onTick(millisUntilFinished: Long) {
                vb.taskkCountDownTxt.text = (millisUntilFinished.toFloat() / 1000f).roundToInt().toString()
            }

            override fun onFinish() {
                vb.taskkCountDownTxt.text = "结束啦"
            }
        })
    }
}