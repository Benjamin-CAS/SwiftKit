package com.mozhimen.componentk.netk.file.download

import android.content.Intent
import com.mozhimen.componentk.netk.file.download.cons.CDownloadParameter

abstract class Downloader(internal val request: DownloadRequest) {


    /**
     * 具体的下载实现
     */
    internal open fun download() {
        DownloaderManager.addIfAbsent(this)
        request.onStart()
    }

    /**
     * 在 Service 中开始下载
     */
    open fun startDownload() {
        DownloaderManager.addIfAbsent(this)
        val intent = Intent("${request.context.packageName}.DownloadService")
        intent.setPackage(request.context.packageName)
        intent.putExtra(CDownloadParameter.EXTRA_URL, request.url)
        request.context.startService(intent)
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Downloader) return false

        if (request.url != other.request.url) return false

        return true
    }

    override fun hashCode(): Int {
        return request.url.hashCode()
    }
}