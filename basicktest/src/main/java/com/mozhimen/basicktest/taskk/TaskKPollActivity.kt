package com.mozhimen.basicktest.taskk

import android.os.Bundle
import com.mozhimen.basick.elemk.activity.bases.BaseActivityVB
import com.mozhimen.basick.utilk.exts.showToast
import com.mozhimen.basick.taskk.temps.TaskKPoll
import com.mozhimen.basicktest.databinding.ActivityTaskkPollBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TaskKPollActivity : BaseActivityVB<ActivityTaskkPollBinding>() {
    private var _taskKPoll: TaskKPoll = TaskKPoll(this)
    override fun initView(savedInstanceState: Bundle?) {
        vb.eventkTaskPollBtnStart.setOnClickListener {
            _taskKPoll.start(5000) {
                withContext(Dispatchers.Main) {
                    System.currentTimeMillis().toString().showToast()
                }
            }
        }
        vb.eventkTaskPollBtnCancel.setOnClickListener {
            _taskKPoll.cancel()
        }
    }
}