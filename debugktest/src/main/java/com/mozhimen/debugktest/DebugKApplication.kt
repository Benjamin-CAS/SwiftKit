package com.mozhimen.debugktest

import com.mozhimen.basick.elemk.application.bases.BaseApplication
import com.mozhimen.componentk.adaptk.AdaptKMgr


/**
 * @ClassName DebugKApplication
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2022/12/16 14:51
 * @Version 1.0
 */
class DebugKApplication : BaseApplication() {

    override fun onCreate() {
        super.onCreate()

        AdaptKMgr.instance.init()
    }
}