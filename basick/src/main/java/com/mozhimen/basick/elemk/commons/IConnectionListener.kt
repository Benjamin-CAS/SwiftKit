package com.mozhimen.basick.elemk.commons

import com.mozhimen.basick.elemk.cons.ENetType


/**
 * @ClassName INetKConnListener
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/2/13 15:02
 * @Version 1.0
 */
interface IConnectionListener {
    fun onDisconnect()
    fun onConnect(type: ENetType)
}