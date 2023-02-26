package com.mozhimen.basicktest.manifestk.permission

import android.os.Bundle
import com.mozhimen.basick.elemk.activity.bases.BaseActivityVB
import com.mozhimen.basick.manifestk.cons.CPermission
import com.mozhimen.basick.manifestk.permission.ManifestKPermission
import com.mozhimen.basick.manifestk.permission.annors.APermissionCheck
import com.mozhimen.basick.manifestk.annors.AManifestKRequire
import com.mozhimen.basick.utilk.content.UtilKIntentSkip
import com.mozhimen.basicktest.databinding.ActivityManifestkPermissionBinding

@AManifestKRequire(CPermission.INTERNET)
@APermissionCheck(CPermission.INTERNET)
class ManifestKPermissionActivity : BaseActivityVB<ActivityManifestkPermissionBinding>() {
    override fun initData(savedInstanceState: Bundle?) {
        //方法一,need APermissionCheck 注解
        ManifestKPermission.initPermissions(this) {
            if (it) {
                super.initData(savedInstanceState)
            } else {
                UtilKIntentSkip.startSettingSelf(this)
            }
        }

        //方法二,need APermissionCheck 注解
        ManifestKPermission.initPermissions(this, onSuccess = {
            initView(savedInstanceState)
        }, onFail = {
            UtilKIntentSkip.startSettingSelf(this)
        })

        //方法三
        ManifestKPermission.initPermissions(this, arrayOf(CPermission.INTERNET)) {
            if (it) {
                initView(savedInstanceState)
            } else {
                UtilKIntentSkip.startSettingSelf(this)
            }
        }
    }
}