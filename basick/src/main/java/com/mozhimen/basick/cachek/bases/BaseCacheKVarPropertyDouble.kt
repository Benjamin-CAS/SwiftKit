package com.mozhimen.basick.cachek.bases

import com.mozhimen.basick.cachek.commons.ICacheKProvider
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty


/**
 * @ClassName BaseCacheKDelegateDouble
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/3/13 15:17
 * @Version 1.0
 */
open class BaseCacheKVarPropertyDouble<P : ICacheKProvider>(
    private val _cacheKProvider: P,
    private val _key: String,
    private val _default: Double = 0.0
) : ReadWriteProperty<Any?, Double> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): Double {
        return _cacheKProvider.getDouble(_key, _default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: Double) {
        _cacheKProvider.putDouble(_key, value)
    }
}