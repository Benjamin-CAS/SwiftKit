package com.mozhimen.componentk.netk.connection

import android.app.Activity
import android.net.ConnectivityManager
import androidx.lifecycle.LifecycleOwner
import com.mozhimen.basick.lintk.optin.annors.ALintKOptIn_ApiInit_ByLazy
import com.mozhimen.basick.elemk.android.content.bases.BaseBroadcastReceiverProxy
import com.mozhimen.basick.lintk.optin.annors.ALintKOptIn_ApiCall_BindLifecycle
import com.mozhimen.basick.manifestk.annors.AManifestKRequire
import com.mozhimen.basick.manifestk.cons.CPermission
import com.mozhimen.basick.elemk.android.content.NetConnectionBroadcastReceiver
import com.mozhimen.basick.elemk.commons.IConnectionListener


/**
 * @ClassName SenseKNetConnProxy
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/2/13 15:41
 * @Version 1.0
 */
@ALintKOptIn_ApiCall_BindLifecycle
@ALintKOptIn_ApiInit_ByLazy
@AManifestKRequire(
    CPermission.ACCESS_NETWORK_STATE,
    CPermission.ACCESS_WIFI_STATE,
    CPermission.ACCESS_FINE_LOCATION,
    CPermission.INTERNET
)
class NetKConnectionProxy<A>(
    _activity: A,
    private val _listener: IConnectionListener,
    private val _receiver: NetConnectionBroadcastReceiver = NetConnectionBroadcastReceiver(),
) : BaseBroadcastReceiverProxy<A>(_activity, _receiver, ConnectivityManager.CONNECTIVITY_ACTION) where A : Activity, A : LifecycleOwner {

    init {
        _receiver.registerListener(_listener)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        _receiver.unRegisterListener(_listener)
        super.onDestroy(owner)
    }
}