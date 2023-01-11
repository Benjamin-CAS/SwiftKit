package com.mozhimen.uicorektest.imagek

import com.mozhimen.basick.elemk.activity.bases.BaseActivityVB
import com.mozhimen.basick.permissionk.annors.APermissionKCheck
import com.mozhimen.basick.permissionk.cons.CPermission
import com.mozhimen.basick.permissionk.annors.APermissionKRequire
import com.mozhimen.uicorektest.databinding.ActivityImagekBindingAdapterBinding


/**
 * @ClassName ImageKBindingAdapterActivity
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2022/11/11 14:12
 * @Version 1.0
 */
@APermissionKRequire(CPermission.SYSTEM_ALERT_WINDOW)
@APermissionKCheck(CPermission.SYSTEM_ALERT_WINDOW)
class ImageKBindingAdapterActivity: BaseActivityVB<ActivityImagekBindingAdapterBinding>()