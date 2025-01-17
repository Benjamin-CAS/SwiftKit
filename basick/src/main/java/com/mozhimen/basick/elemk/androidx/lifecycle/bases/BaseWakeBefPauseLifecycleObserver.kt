package com.mozhimen.basick.elemk.androidx.lifecycle.bases

import androidx.lifecycle.LifecycleOwner
import com.mozhimen.basick.elemk.androidx.lifecycle.commons.IDefaultLifecycleObserver
import com.mozhimen.basick.lintk.optin.annors.ALintKOptIn_ApiInit_ByLazy
import com.mozhimen.basick.lintk.optin.annors.ALintKOptIn_ApiCall_BindLifecycle
import com.mozhimen.basick.utilk.androidx.lifecycle.runOnMainThread


/**
 * @ClassName BaseLifecycleObserverForView
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/2/24 17:47
 * @Version 1.0
 */
@ALintKOptIn_ApiCall_BindLifecycle
@ALintKOptIn_ApiInit_ByLazy
open class BaseWakeBefPauseLifecycleObserver : IDefaultLifecycleObserver {
    override fun bindLifecycle(owner: LifecycleOwner) {
        owner.runOnMainThread {
            owner.lifecycle.removeObserver(this@BaseWakeBefPauseLifecycleObserver)
            owner.lifecycle.addObserver(this@BaseWakeBefPauseLifecycleObserver)
        }
    }

    override fun onPause(owner: LifecycleOwner) {
        owner.lifecycle.removeObserver(this@BaseWakeBefPauseLifecycleObserver)
    }
}