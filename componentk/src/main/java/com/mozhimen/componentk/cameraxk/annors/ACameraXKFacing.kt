package com.mozhimen.componentk.cameraxk.annors

import androidx.annotation.IntDef

/**
 * @ClassName CameraXKFacing
 * @Description TODO
 * @Author Kolin Zhao / Mozhimen
 * @Date 2022/7/18 17:12
 * @Version 1.0
 */
@IntDef(value = [ACameraXKFacing.FRONT, ACameraXKFacing.BACK])
annotation class ACameraXKFacing {
    companion object {
        const val FRONT = 0
        const val BACK = 1
    }
}