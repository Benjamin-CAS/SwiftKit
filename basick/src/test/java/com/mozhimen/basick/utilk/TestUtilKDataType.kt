package com.mozhimen.basick.utilk

import com.mozhimen.basick.utilk.kotlin.UtilKDataType
import com.mozhimen.basick.utilk.kotlin.isObjPrimitive
import com.mozhimen.basick.utilk.kotlin.printlog
import org.junit.Assert
import org.junit.Test

/**
 * @ClassName UtilKDataType
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Date 2022/8/30 23:57
 * @Version 1.0
 */
class TestUtilKDataType {
    @Test
    fun isTypeMatch() {
        Assert.assertTrue(UtilKDataType.isTypeMatch("123", String::class.java))
    }

    @Test
    fun getTypeName() {
        UtilKDataType.getTypeName(0x000000).printlog()
    }

    @Test
    fun isObjPrimitive() {
        2.isObjPrimitive().printlog()
    }
}