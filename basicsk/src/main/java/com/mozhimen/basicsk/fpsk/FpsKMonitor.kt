package com.mozhimen.basicsk.fpsk

import android.content.Context
import android.content.Intent
import android.graphics.PixelFormat
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.TextView
import com.mozhimen.basicsk.R
import com.mozhimen.basicsk.logk.LogK
import com.mozhimen.basicsk.stackk.StackK
import com.mozhimen.basicsk.stackk.commons.IStackKListener
import com.mozhimen.basicsk.utilk.UtilKGlobal
import java.text.DecimalFormat

/**
 * @ClassName FpsKMonitor
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Date 2022/3/31 17:12
 * @Version 1.0
 */
object FpsKMonitor {

    private val _fpsKViewer = FpsKViewer()

    fun toggle() {
        _fpsKViewer.toggle()
    }

    fun listener(listener: IFpsKListener) {
        _fpsKViewer.addListener(listener)
    }

    interface IFpsKListener {
        fun onFrame(fps: Double)
    }

    private class FpsKViewer {
        private var _params = WindowManager.LayoutParams()
        private var _isShow = false
        private val _context = UtilKGlobal.instance.getApp()!!
        private var _fpsView =
            LayoutInflater.from(_context).inflate(R.layout.view_fpsk, null, false) as TextView
        private val _frameMonitor = FrameMonitor()

        private val _decimal = DecimalFormat("#.0 fps")
        private var _windowManager: WindowManager? = null

        init {
            _windowManager = _context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            _params.width = WindowManager.LayoutParams.WRAP_CONTENT
            _params.height = WindowManager.LayoutParams.WRAP_CONTENT
            _params.flags =
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE or WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL

            _params.format = PixelFormat.TRANSLUCENT
            _params.gravity = Gravity.RIGHT or Gravity.TOP

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                _params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY
            } else {
                _params.type = WindowManager.LayoutParams.TYPE_TOAST
            }

            _frameMonitor.addListener(object : IFpsKListener {
                override fun onFrame(fps: Double) {
                    _fpsView.text = _decimal.format(fps)
                }
            })

            StackK.addFrontBackListener(object : IStackKListener {
                override fun onChanged(isFront: Boolean) {
                    if (isFront) {
                        play()
                    } else {
                        stop()
                    }
                }
            })
        }

        private fun stop() {
            _frameMonitor.stop()
            if (_isShow) {
                _isShow = false
                _windowManager!!.removeView(_fpsView)
            }
        }

        private fun play() {
            if (!hasOverlayPermission()) {
                startOverlaySettingActivity()
                LogK.e("app has no overlay permission")
                return
            }

            _frameMonitor.start()
            if (!_isShow) {
                _isShow = true
                _windowManager!!.addView(_fpsView, _params)
            }

        }

        private fun startOverlaySettingActivity() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                _context.startActivity(
                    Intent(
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + _context.packageName)
                    ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                )
            }
        }

        private fun hasOverlayPermission(): Boolean {
            return Build.VERSION.SDK_INT < Build.VERSION_CODES.M || Settings.canDrawOverlays(_context)
        }

        fun toggle() {
            if (_isShow) {
                stop()
            } else {
                play()
            }
        }

        fun addListener(listenerI: IFpsKListener) {
            _frameMonitor.addListener(listenerI)
        }
    }
}