package com.mozhimen.debugk.commons

import androidx.lifecycle.MutableLiveData
import com.mozhimen.basick.elemk.viewmodel.bases.BaseViewModel


/**
 * @ClassName DebugKNavigateKViewModel
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2022/11/22 10:59
 * @Version 1.0
 */
class DebugKNavigateKViewModel : BaseViewModel() {
    val liveFragmentId = MutableLiveData<Int?>(null)
}