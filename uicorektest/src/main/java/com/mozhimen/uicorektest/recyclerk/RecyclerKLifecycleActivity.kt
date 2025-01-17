package com.mozhimen.uicorektest.recyclerk

import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mozhimen.basick.elemk.androidx.appcompat.bases.BaseActivityVB
import com.mozhimen.basick.lintk.optin.annors.ALintKOptIn_ApiInit_ByLazy
import com.mozhimen.basick.elemk.mos.MKey
import com.mozhimen.basick.lintk.optin.annors.ALintKOptIn_ApiCall_BindLifecycle
import com.mozhimen.basick.utilk.android.widget.showToast
import com.mozhimen.uicorek.adapterk.AdapterKRecyclerVB
import com.mozhimen.uicorektest.R
import com.mozhimen.uicorektest.BR
import com.mozhimen.uicorektest.databinding.ActivityRecyclerkLifecycleBinding
import com.mozhimen.uicorektest.databinding.ItemRecyclerkLifecycleBinding

class RecyclerKLifecycleActivity : BaseActivityVB<ActivityRecyclerkLifecycleBinding>() {
    @OptIn(ALintKOptIn_ApiInit_ByLazy::class, ALintKOptIn_ApiCall_BindLifecycle::class)
    override fun initView(savedInstanceState: Bundle?) {
        val list = listOf(MKey("1", "1"), MKey("2", "2"))
        vb.recyclerkLifecycle.bindLifecycle(this)
        vb.recyclerkLifecycle.layoutManager = LinearLayoutManager(this)
        vb.recyclerkLifecycle.addItemDecoration(DividerItemDecoration(this, LinearLayout.VERTICAL))
        vb.recyclerkLifecycle.adapter =
            AdapterKRecyclerVB<MKey, ItemRecyclerkLifecycleBinding>(list, R.layout.item_recyclerk_lifecycle, BR.item_recyclerk_lifecycle) { holder, itemData, position, currentSelectPos ->
                holder.vb.itemRecyclerkLifecycleBox.setOnClickListener {
                    position.toString().showToast()
                }
            }.apply {
                onItemSelected(0)
            }
    }

}