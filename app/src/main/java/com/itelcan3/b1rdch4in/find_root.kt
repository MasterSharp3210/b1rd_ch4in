package com.itelcan3.b1rdch4in

import java.io.BufferedReader
import java.io.InputStreamReader

object FindRoot {

    fun isRooted(): Boolean {
        return try {
            val process = Runtime.getRuntime().exec(arrayOf("su", "-c", "id"))

            val reader = BufferedReader(
                InputStreamReader(process.inputStream)
            )

            val output = reader.readText()

            process.waitFor()

            process.exitValue() == 0 && output.contains("uid=0")

        } catch (e: Exception) {
            false
        }
    }

}