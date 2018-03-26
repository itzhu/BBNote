package me.itzhu.common.util

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.RandomAccessFile
import java.util.*

/**
 * Created by itzhu on 2018/1/15.
 * desc
 * Installtion ID
 * 以上几种方式都或多或少存在一定的局限性或者Bug，如果并不是确实需要对硬件本身进行绑定，使用自己生成的UUID也是一个不错的选择，因为该方法无需访问设备的资源，也跟设备类型无关。
 * 这种方式的原理是在程序安装后第一次运行时生成一个ID，该方式和设备唯一标识不一样，不同的应用程序会产生不同的ID，同一个程序重新安装也会不同。所以这不是设备的唯一ID，但是可以保证每个用户的ID是不同的。可以说是用来标识每一份应用程序的唯一ID（即Installtion ID），可以用来跟踪应用的安装数量等。
 */
object Installation {
    private var sID: String? = null
    private val INSTALLATION = "INSTALLATION"
    @Synchronized
    fun id(context: Context): String? {
        if (sID == null) {
            val installation = File(context.filesDir, INSTALLATION)
            try {
                if (!installation.exists()) writeInstallationFile(installation)
                sID = readInstallationFile(installation)
            } catch (e: Exception) {
                throw RuntimeException(e)
            }
        }
        return sID
    }

    @Throws(IOException::class)
    private fun readInstallationFile(installation: File): String {
        val f = RandomAccessFile(installation, "r")
        val bytes = ByteArray(f.length().toInt())
        f.readFully(bytes)
        f.close()
        return String(bytes)
    }

    @Throws(IOException::class)
    private fun writeInstallationFile(installation: File) {
        val out = FileOutputStream(installation)
        val id = UUID.randomUUID().toString()
        out.write(id.toByteArray())
        out.close()
    }
}