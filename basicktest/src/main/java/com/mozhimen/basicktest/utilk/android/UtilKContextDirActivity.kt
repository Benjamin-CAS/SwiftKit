package com.mozhimen.basicktest.utilk.android

import com.mozhimen.basick.elemk.androidx.appcompat.bases.BaseActivityVB
import com.mozhimen.basick.manifestk.cons.CPermission
import com.mozhimen.basick.manifestk.permission.ManifestKPermission
import com.mozhimen.basick.manifestk.permission.annors.APermissionCheck
import com.mozhimen.basick.utilk.android.app.UtilKLaunchActivity
import com.mozhimen.basick.utilk.android.content.UtilKContextDir
import com.mozhimen.basick.utilk.android.os.UtilKBuildVersion
import com.mozhimen.basick.utilk.android.util.i
import com.mozhimen.basicktest.databinding.ActivityUtilkContextDirBinding

/**
 * @ClassName UtilKContextDirActivity
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/4/17 16:24
 * @Version 1.0
 */
@APermissionCheck(CPermission.READ_EXTERNAL_STORAGE, CPermission.READ_EXTERNAL_STORAGE)
class UtilKContextDirActivity : BaseActivityVB<ActivityUtilkContextDirBinding>() {
    override fun initData(savedInstanceState: android.os.Bundle?) {
        ManifestKPermission.requestPermissions(this) {
            if (it) {
                super.initData(savedInstanceState)
            } else {
                UtilKLaunchActivity.startSettingAppDetails(this)
            }
        }
    }

    override fun initView(savedInstanceState: android.os.Bundle?) {
        UtilKContextDir.Internal.getCacheDir(this).absolutePath.i()///data/user/0/com.mozhimen.basicktest/cache
        UtilKContextDir.Internal.getFilesDir(this).absolutePath.i()///data/user/0/com.mozhimen.basicktest/files
        if (UtilKBuildVersion.isAfterV_24_7_N()) {
            UtilKContextDir.Internal.getDataDir(this).absolutePath.i()///data/user/0/com.mozhimen.basicktest
        }
        UtilKContextDir.Internal.getObbDir(this).absolutePath.i()///storage/emulated/0/Android/obb/com.mozhimen.basicktest
        UtilKContextDir.Internal.getObbDirs(this)?.asList()?.joinToString { it.absolutePath }?.i()///storage/emulated/0/Android/obb/com.mozhimen.basicktest
        UtilKContextDir.Internal.getCodeCacheDir(this).absolutePath.i()///data/user/0/com.mozhimen.basicktest/code_cache
        UtilKContextDir.Internal.getNoBackupFilesDir(this).absolutePath.i()///data/user/0/com.mozhimen.basicktest/no_backup
        UtilKContextDir.Internal.getPackageCodePath(this).i()///data/app/com.mozhimen.basicktest-2/base.apk
        UtilKContextDir.Internal.getPackageResourcePath(this).i()///data/app/com.mozhimen.basicktest-2/base.apk

        UtilKContextDir.External.getCacheDir(this)?.absolutePath?.i()///storage/emulated/0/Android/data/com.mozhimen.basicktest/cache
        UtilKContextDir.External.getFilesRootDir(this)?.absolutePath?.i()///storage/emulated/0/Android/data/com.mozhimen.basicktest/files
        UtilKContextDir.External.getFilesAlarmsDir(this)?.absolutePath?.i()///storage/emulated/0/Android/data/com.mozhimen.basicktest/files/Alarms
        if (UtilKBuildVersion.isAfterV_29_10_Q()) {
            UtilKContextDir.External.getFilesAudiobooksDir(this)?.absolutePath?.i()
        }
        UtilKContextDir.External.getFilesDCIMDir(this)?.absolutePath?.i()///storage/emulated/0/Android/data/com.mozhimen.basicktest/files/DCIM
        UtilKContextDir.External.getFilesDocumentsDir(this)?.absolutePath?.i()///storage/emulated/0/Android/data/com.mozhimen.basicktest/files/Documents
        UtilKContextDir.External.getFilesDownloadsDir(this)?.absolutePath?.i()///storage/emulated/0/Android/data/com.mozhimen.basicktest/files/Download
        UtilKContextDir.External.getFilesMoviesDir(this)?.absolutePath?.i()///storage/emulated/0/Android/data/com.mozhimen.basicktest/files/Movies
        UtilKContextDir.External.getFilesMusicDir(this)?.absolutePath?.i()///storage/emulated/0/Android/data/com.mozhimen.basicktest/files/Music
        UtilKContextDir.External.getFilesNotificationsDir(this)?.absolutePath?.i()///storage/emulated/0/Android/data/com.mozhimen.basicktest/files/Notifications
        UtilKContextDir.External.getFilesPicturesDir(this)?.absolutePath?.i()///storage/emulated/0/Android/data/com.mozhimen.basicktest/files/Pictures
        UtilKContextDir.External.getFilesPodcastsDir(this)?.absolutePath?.i()///storage/emulated/0/Android/data/com.mozhimen.basicktest/files/Podcasts
        if (UtilKBuildVersion.isAfterV_31_11_S()) {
            UtilKContextDir.External.getFilesRecordingsDir(this)?.absolutePath?.i()
        }
        if (UtilKBuildVersion.isAfterV_29_10_Q()) {
            UtilKContextDir.External.getFilesScreenshotsDir(this)?.absolutePath?.i()
        }
    }
}