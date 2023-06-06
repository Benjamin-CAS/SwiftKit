package com.mozhimen.basick.taskk.executor.bases

import com.mozhimen.basick.elemk.commons.IValue1Listener
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

/**
 * @ClassName BaseGlobalCoroutineExceptionHandler
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2023/6/6 23:42
 * @Version 1.0
 */
typealias IGlobalCoroutineExceptionHandlerListener = IValue1Listener<Throwable>

open class BaseGlobalCoroutineExceptionHandler(private val _listener: IGlobalCoroutineExceptionHandlerListener? = null) : CoroutineExceptionHandler {
    override val key: CoroutineContext.Key<*> = CoroutineExceptionHandler
    override fun handleException(context: CoroutineContext, exception: Throwable) {
        exception.printStackTrace()
        _listener?.invoke(exception)
    }
}