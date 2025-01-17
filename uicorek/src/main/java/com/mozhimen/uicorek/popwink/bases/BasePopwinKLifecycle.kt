package com.mozhimen.uicorek.popwink.bases

import android.content.Context
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.mozhimen.uicorek.popwink.bases.BasePopwinK

/**
 * @ClassName PopwinKTip
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Date 2022/5/31 23:09
 * @Version 1.0
 */
open class BasePopwinKLifecycle(context: Context) : BasePopwinK(context), LifecycleOwner {
    protected var _lifecycleRegistry: LifecycleRegistry? = null

    init {
        _lifecycleRegistry = LifecycleRegistry(this)
    }

    override fun onViewCreated(contentView: View) {
        super.onViewCreated(contentView)
        _lifecycleRegistry?.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    override fun onShowing() {
        super.onShowing()
        _lifecycleRegistry?.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    override fun onDismiss() {
        _lifecycleRegistry?.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        super.onDismiss()
    }

    override fun onDestroy() {
        _lifecycleRegistry?.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        super.onDestroy()
    }

    override fun getLifecycle(): Lifecycle {
        return _lifecycleRegistry!!
    }
}