package com.mozhimen.basick.utilk.java.nio

import java.nio.ByteBuffer


/**
 * @ClassName UtilKBuffer
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2023/7/24 18:45
 * @Version 1.0
 */
fun ByteBuffer.toByteArray(): ByteArray =
    UtilKByteBuffer.toByteArray(this)

object UtilKByteBuffer {

    @JvmStatic
    fun toByteArray(byteBuffer: ByteBuffer): ByteArray {
        byteBuffer.rewind()    // Rewind the buffer to zero
        val bytes = ByteArray(byteBuffer.remaining())
        byteBuffer.get(bytes)   // Copy the buffer into a byte array
        return bytes // Return the byte array
    }
}