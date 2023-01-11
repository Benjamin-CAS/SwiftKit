package com.mozhimen.basick.elemk.receiver.bases

import android.app.DownloadManager.ACTION_DOWNLOAD_COMPLETE
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.mozhimen.basick.permissionk.cons.CPermission
import com.mozhimen.basick.permissionk.annors.APermissionRequire
import com.mozhimen.basick.utilk.app.UtilKAppInstall


/**
 * @ClassName LoadKReceiverInstall
 * @Description if you use it, you must register in manifest first
 *

 * 权限
Permission.INSTALL_PACKAGES, Permission.REQUEST_INSTALL_PACKAGES, Permission.READ_INSTALL_SESSIONS, Permission.REPLACE_EXISTING_PACKAGE)

 * 继承
class ElemKDownloadInstallReceiver : BaseDownloadInstallReceiver("")

 * 静态注册
<receiver android:name=".InstallReceiver"
android:exported="false">
<intent-filter>
<action android:name="android.intent.action.DOWNLOAD_COMPLETE" />
<action android:name="android.intent.action.DOWNLOAD_NOTIFICATION_CLICKED"/>
</intent-filter>
</receiver>

 * AndroidManifest.xml sdk>24
<provider
android:name="androidx.core.content.FileProvider"
android:authorities="包名.fileprovider"
android:exported="false"
android:grantUriPermissions="true">
<meta-data
android:name="android.support.FILE_PROVIDER_PATHS"
android:resource="@xml/file_paths"  />
</provider>

 * file_paths.xml sdk>24
<paths>
<files-path
name="files-path"
path="." />
</paths>

 * @Author Kolin Zhao / Mozhimen
 * @Date 2022/6/13 12:04
 * @Version 1.0
 */
@APermissionRequire(CPermission.INSTALL_PACKAGES, CPermission.REQUEST_INSTALL_PACKAGES, CPermission.READ_INSTALL_SESSIONS, CPermission.REPLACE_EXISTING_PACKAGE)
open class BaseDownloadInstallReceiver(private val _apkPathWithName: String) : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            ACTION_DOWNLOAD_COMPLETE -> {
                UtilKAppInstall.installAuto(_apkPathWithName)
            }
        }
    }
}