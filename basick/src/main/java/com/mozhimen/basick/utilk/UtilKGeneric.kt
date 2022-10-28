package com.mozhimen.basick.utilk

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * @ClassName UtilKGeneric
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Date 2022/10/22 3:53
 * @Version 1.0
 */
object UtilKGeneric {
    abstract class UtilKTypeRef<T> // 自定义的类,用来包装泛型

    /**
     * 获取当前<>里面的泛型实例
     * @param index Int
     * @return Type?
     */
    @JvmStatic
    inline fun <reified T> getGenericType(index: Int = 0): Type? =
        object : UtilKTypeRef<T>() {}::class.java
            .genericSuperclass
            .let { it as ParameterizedType }
            .actualTypeArguments.filterIsInstance<Class<*>>()
            .run {
                if (this.isNotEmpty() && index in this.indices)
                    this[index]
                else
                    null
            }

    /**
     * 获取泛型类
     * @param obj Any
     * @param index Int
     * @return Class<*>?
     */
    @JvmStatic
    fun getParentGenericTypeClazz(obj: Any, index: Int = 0): Class<*>? =
        getParentGenericType(obj, index) as? Class<*>?

    /**
     * 获取泛型type
     * @param obj Any
     * @param index Int
     * @return Type?
     */
    @JvmStatic
    fun getParentGenericType(obj: Any, index: Int = 0): Type? =
        obj::class.java
            .genericSuperclass
            .let { it as ParameterizedType }
            .actualTypeArguments.filterIsInstance<Class<*>>()
            .run {
                if (this.isNotEmpty() && index in this.indices)
                    this[index]
                else
                    null
            }

    /**
     * 获取继承父类的泛型类
     * @param index Int
     * @return Class<*>?
     */
    @JvmStatic
    inline fun <reified T> getParentGenericTypeClazz(index: Int = 0): Class<*>? =
        getParentGenericType<T>(index) as? Class<*>?

    /**
     * 获取继承父类的泛型Type
     * @param index Int
     * @return Type?
     */
    @JvmStatic
    inline fun <reified T> getParentGenericType(index: Int = 0): Type? =
        T::class.java
            .genericSuperclass
            .let { it as ParameterizedType }
            .actualTypeArguments.filterIsInstance<Class<*>>()
            .run {
                if (this.isNotEmpty() && index in this.indices)
                    this[index]
                else
                    null
            }
}