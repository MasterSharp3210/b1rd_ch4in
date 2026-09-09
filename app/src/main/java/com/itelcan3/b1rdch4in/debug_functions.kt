package com.itelcan3.b1rdch4in

import android.content.Context
import android.content.Intent
import android.provider.Settings
import java.io.OutputStreamWriter

object DebugFunctions {

    fun reboot() {
        executeRootCommand("reboot")
    }

    fun shutdown() {
        executeRootCommand("reboot -p")
    }

    fun forceReset() {
        executeRootCommand("reboot -p -f")
    }

    fun rebootRecovery() {
        executeRootCommand("reboot recovery")
    }

    fun rebootBootloader() {
        executeRootCommand("reboot bootloader")
    }

    fun openSettings(context: Context) {
        context.startActivity(Intent(Settings.ACTION_SETTINGS))
    }

    private fun executeRootCommand(command: String) {
        try {
            val process = Runtime.getRuntime().exec("su")

            val writer = OutputStreamWriter(process.outputStream)

            writer.write(command)
            writer.write("\n")
            writer.write("exit\n")
            writer.flush()
            writer.close()

            process.waitFor()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}