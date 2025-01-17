package com.mozhimen.basick.taskk.temps

import android.os.CountDownTimer
import com.mozhimen.basick.lintk.optin.annors.ALintKOptIn_ApiInit_ByLazy
import com.mozhimen.basick.lintk.optin.annors.ALintKOptIn_ApiCall_BindLifecycle
import com.mozhimen.basick.taskk.bases.BaseWakeBefPauseTaskK

/**
 * @ClassName UtilKCountDown
 * @Description TODO
 * @Author Kolin Zhao / Mozhimen
 * @Date 2022/9/26 15:57
 * @Version 1.0
 */
interface ITaskKCountDownListener {
    fun onTick(millisUntilFinished: Long)
    fun onFinish()
}

open class TaskKCountDownCallback : ITaskKCountDownListener {
    override fun onTick(millisUntilFinished: Long) {}
    override fun onFinish() {}
}

@ALintKOptIn_ApiCall_BindLifecycle
@ALintKOptIn_ApiInit_ByLazy
class TaskKCountDown : BaseWakeBefPauseTaskK() {

    private var _taskKCountDownListener: ITaskKCountDownListener? = null
    private var _countDownTimer: CountDownTimer? = null

    override fun isActive() = _countDownTimer != null

    fun start(countDownMilliseconds: Long, listener: ITaskKCountDownListener? = null) {
        if (isActive()) return
        listener?.let { _taskKCountDownListener = it }
        _countDownTimer = UtilKCountDownTimer(countDownMilliseconds)
        _countDownTimer!!.start()
    }

    override fun cancel() {
        if (!isActive()) return
        _taskKCountDownListener = null
        _countDownTimer?.cancel()
        _countDownTimer = null
    }

    private inner class UtilKCountDownTimer(countDownMilliseconds: Long) : CountDownTimer(countDownMilliseconds, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            _taskKCountDownListener?.onTick(millisUntilFinished)
        }

        override fun onFinish() {
            _taskKCountDownListener?.onFinish()
        }
    }
}