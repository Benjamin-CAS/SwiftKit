package com.mozhimen.componentk.navigatek

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import com.mozhimen.basick.lintk.optin.annors.AOptInInitByLazy
import com.mozhimen.basick.elemk.androidx.lifecycle.bases.BaseWakeBefDestroyLifecycleObserver
import com.mozhimen.basick.lintk.optin.annors.AOptInNeedCallBindLifecycle
import com.mozhimen.basick.utilk.android.util.et


/**
 * @ClassName NavigateKProxy
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/2/17 16:44
 * @Version 1.0
 */
@AOptInNeedCallBindLifecycle
@AOptInInitByLazy
class NavigateKProxy<A>(
    private val _activity: A,
    private val _fragmentLayoutId: Int,
    vararg val _fragments: Class<*>
) :
    BaseWakeBefDestroyLifecycleObserver() where A : LifecycleOwner, A : FragmentActivity {
    private val liveFragmentId = MutableLiveData<Int?>(null)
    private val liveSetPopupFlag = MutableLiveData<Boolean?>(null)
    lateinit var navController: NavController
    var currentItemId: Int = 0
        set(value) {
            if (value == -1 || !this::navController.isInitialized) return
            navController.navigate(value)
            field = value
        }
        get() {
            if (field != 0 || !this::navController.isInitialized) return field
            val fragmentId: Int = navController.currentDestination?.id ?: NavigateK.getId(_fragments.getOrNull(0) ?: return 0)
            return fragmentId.also { field = it }
        }

    override fun onDestroy(owner: LifecycleOwner) {
        liveFragmentId.value = null
        liveSetPopupFlag.value = null
        super.onDestroy(owner)
    }

    override fun onCreate(owner: LifecycleOwner) {
        navController = NavigateK.buildNavGraph(_activity, _fragmentLayoutId, _fragments.toList(), currentItemId)
        liveFragmentId.observe(_activity) {
            if (it != null) {
                genNavigateK(it)
            } else {
                "please add this destination to list".et(TAG)
            }
        }
        liveSetPopupFlag.observe(_activity) {
            if (it != null) {
                navController.popBackStack()
            }
        }
        super.onCreate(owner)
    }

    private fun genNavigateK(id: Int?) {
        if (id != null && navController.findDestination(id) != null && navController.currentDestination?.id != id) {
            currentItemId = id
        }
    }
}