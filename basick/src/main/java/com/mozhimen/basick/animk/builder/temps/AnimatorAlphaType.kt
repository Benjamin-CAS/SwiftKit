package com.mozhimen.basick.animk.builder.temps

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import androidx.annotation.FloatRange
import com.mozhimen.basick.animk.builder.bases.BaseAnimatorType
import com.mozhimen.basick.animk.builder.commons.IAnimatorUpdateListener
import com.mozhimen.basick.animk.builder.mos.MAnimKConfig

/**
 * @ClassName DrawableAlphaType
 * @Description TODO
 * @Author mozhimen / Kolin Zhao
 * @Date 2022/11/20 17:11
 * @Version 1.0
 */
open class AnimatorAlphaType : BaseAnimatorType<AnimatorAlphaType>() {
    override lateinit var _animator: Animator
    protected var _alphaFrom = 0f
    protected var _alphaTo = 1f

    fun setAlpha(@FloatRange(from = 0.0, to = 1.0) fromAlpha: Float, @FloatRange(from = 0.0, to = 1.0) toAlpha: Float): AnimatorAlphaType {
        _alphaFrom = fromAlpha
        _alphaTo = toAlpha
        return this
    }

    open fun addAnimatorUpdateListener(listener: IAnimatorUpdateListener): AnimatorAlphaType {
        (_animator as ObjectAnimator).addUpdateListener {
            listener.onChange(it.animatedValue as Int)
        }
        return this
    }

    open fun addAnimatorListener(listener: AnimatorListenerAdapter): AnimatorAlphaType {
        (_animator as ObjectAnimator).addListener(listener)
        return this
    }

    override fun buildAnimator(animKConfig: MAnimKConfig): Animator {
        _animator = ObjectAnimator.ofInt(_alphaFrom.toInt() * 255, _alphaTo.toInt() * 255)
        formatAnimator(animKConfig, _animator)
        return _animator
    }
}