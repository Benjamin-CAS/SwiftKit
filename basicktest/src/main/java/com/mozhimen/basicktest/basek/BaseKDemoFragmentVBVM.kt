package com.mozhimen.basicktest.basek

import android.os.Bundle
import com.mozhimen.basick.basek.BaseKFragmentVBVM
import com.mozhimen.basicktest.databinding.FragmentBasekFragmentVbvmBinding

class BaseKDemoFragmentVBVM : BaseKFragmentVBVM<FragmentBasekFragmentVbvmBinding, BaseKDemoViewModel>() {

    override fun FragmentBasekFragmentVbvmBinding.bindViewVM() {
        vm = vm
    }

    override fun initData(savedInstanceState: Bundle?) {
        vb.basekDemoFragmentVbvmBtn.setOnClickListener {
            vm.genNum()
        }
    }
}