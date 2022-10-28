package com.mozhimen.componentktest

import com.mozhimen.basick.extsk.printlog
import com.mozhimen.componentk.navigatek.NavigateK
import com.mozhimen.componentktest.navigatek.fragments.FirstFragment
import org.junit.Test

import org.junit.Assert.*
import java.lang.Math.abs

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        FirstFragment::class.java.name.toString().printlog()
        FirstFragment::class.java.canonicalName?.toString().printlog()
        NavigateK.getId(FirstFragment::class.java).printlog()
        abs(FirstFragment::class.java.name.hashCode()).printlog()
    }
}