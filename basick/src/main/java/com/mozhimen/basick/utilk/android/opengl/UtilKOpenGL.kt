package com.mozhimen.basick.utilk.android.opengl

import android.opengl.GLES20
import android.opengl.Matrix
import android.util.Log
import com.mozhimen.basick.BuildConfig
import com.mozhimen.basick.utilk.bases.BaseUtilK
import com.mozhimen.basick.utilk.android.content.UtilKAsset

/**
 * @ClassName UtilKOpenGL
 * @Description TODO
 * @Author Kolin Zhao / Mozhimen
 * @Date 2022/6/16 12:32
 * @Version 1.0
 */
object UtilKOpenGL : BaseUtilK() {

    @JvmStatic
    fun useTexParameterf() {
        //设置缩小过滤为使用纹理中坐标最接近的一个像素的颜色作为需要绘制的像素颜色
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, GLES20.GL_NEAREST.toFloat())
        //设置放大过滤为使用纹理中坐标最接近的若干个颜色，通过加权平均算法得到需要绘制的像素颜色
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, GLES20.GL_LINEAR.toFloat())
        //设置环绕方向S，截取纹理坐标到[1/2n,1-1/2n]。将导致永远不会与border融合
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, GLES20.GL_CLAMP_TO_EDGE.toFloat())
        //设置环绕方向T，截取纹理坐标到[1/2n,1-1/2n]。将导致永远不会与border融合
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, GLES20.GL_CLAMP_TO_EDGE.toFloat())
    }

    @JvmStatic
    fun useTexParameterf(
        glWrapS: Int,
        glWrapT: Int,
        glMinFilter: Int,
        glMagFilter: Int
    ) {
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_S, glWrapS.toFloat())
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_WRAP_T, glWrapT.toFloat())
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER, glMinFilter.toFloat())
        GLES20.glTexParameterf(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER, glMagFilter.toFloat())
    }

    @JvmStatic
    fun genTexturesAndParameterf(
        size: Int,
        textures: IntArray,
        start: Int,
        glFormat: Int,
        width: Int,
        height: Int
    ) {
        GLES20.glGenTextures(size, textures, start)
        for (i in 0 until size) {
            GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textures[i])
            GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D, 0, glFormat, width, height, 0, glFormat, GLES20.GL_UNSIGNED_BYTE, null)
            useTexParameterf()
        }
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, 0)
    }

    @JvmStatic
    fun bindFrameBuffer(
        frameBufferId: Int,
        textureId: Int
    ) {
        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, frameBufferId)
        GLES20.glFramebufferTexture2D(GLES20.GL_FRAMEBUFFER, GLES20.GL_COLOR_ATTACHMENT0, GLES20.GL_TEXTURE_2D, textureId, 0)
    }

    @JvmStatic
    fun unBindFrameBuffer() {
        GLES20.glBindFramebuffer(GLES20.GL_FRAMEBUFFER, 0)
    }

    @JvmStatic
    fun getShowMatrix(
        matrix: FloatArray,
        imgWidth: Int,
        imgHeight: Int,
        viewWidth: Int,
        viewHeight: Int
    ) {
        if (imgHeight > 0 && imgWidth > 0 && viewWidth > 0 && viewHeight > 0) {
            val sWhView = viewWidth.toFloat() / viewHeight
            val sWhImg = imgWidth.toFloat() / imgHeight
            val projection = FloatArray(16)
            val camera = FloatArray(16)
            if (sWhImg > sWhView) {
                Matrix.orthoM(projection, 0, -sWhView / sWhImg, sWhView / sWhImg, -1f, 1f, 1f, 3f)
            } else {
                Matrix.orthoM(projection, 0, -1f, 1f, -sWhImg / sWhView, sWhImg / sWhView, 1f, 3f)
            }
            Matrix.setLookAtM(camera, 0, 0f, 0f, 1f, 0f, 0f, 0f, 0f, 1f, 0f)
            Matrix.multiplyMM(matrix, 0, projection, 0, camera, 0)
        }
    }

    @JvmStatic
    fun createGlProgramByRes(vert: String, frag: String): Int =
        createGlProgram(UtilKAsset.asset2Str3(vert), UtilKAsset.asset2Str3(frag))

    /**
     * 创建GL程序
     * @param vertexSource String
     * @param fragmentSource String
     * @return Int
     */
    @JvmStatic
    fun createGlProgram(vertexSource: String, fragmentSource: String): Int {
        val vertex = loadShader(GLES20.GL_VERTEX_SHADER, vertexSource)
        if (vertex == 0) return 0
        val fragment = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentSource)
        if (fragment == 0) return 0
        var program = GLES20.glCreateProgram()
        if (program != 0) {
            GLES20.glAttachShader(program, vertex)
            GLES20.glAttachShader(program, fragment)
            GLES20.glLinkProgram(program)
            val linkStatus = IntArray(1)
            GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0)
            if (linkStatus[0] != GLES20.GL_TRUE) {
                glError(1, "createGlProgram Could not link program:" + GLES20.glGetProgramInfoLog(program))
                GLES20.glDeleteProgram(program)
                program = 0
            }
        }
        return program
    }

    /**
     * 加载shader
     * @param shaderType Int
     * @param source String?
     * @return Int
     */
    @JvmStatic
    fun loadShader(shaderType: Int, source: String): Int {
        var shader = GLES20.glCreateShader(shaderType)
        if (0 != shader) {
            GLES20.glShaderSource(shader, source)
            GLES20.glCompileShader(shader)
            val compiled = IntArray(1)
            GLES20.glGetShaderiv(shader, GLES20.GL_COMPILE_STATUS, compiled, 0)
            if (compiled[0] == 0) {
                glError(1, "loadShader Could not compile shader $shaderType")
                glError(1, "loadShader GLES20 Error ${GLES20.glGetShaderInfoLog(shader)}")
                GLES20.glDeleteShader(shader)
                shader = 0
            }
        }
        return shader
    }

    @JvmStatic
    fun glError(code: Int, index: Any) {
        if (BuildConfig.DEBUG && code != 0) {
            Log.e(TAG, "glError: $code: $index")
        }
    }
}