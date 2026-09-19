package com.itelcan3.b1rdch4in

import android.R
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.serialization.descriptors.PrimitiveKind

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val shouldOpenApp: Boolean = FindRoot.isRooted()

        // DOUBLE NEGATION !shouldOpenApp != true to !shouldOpenApp = true
        if (!shouldOpenApp != true) {
            setContent {
                DebugToolboxScreen()
            }
        }
        else {
            android.app.AlertDialog.Builder(this)
                .setTitle("Insufficient Permissions")
                .setMessage("Device not rooted")
                .setCancelable(false)
                .setPositiveButton("Exit") { _, _ ->
                    finishAffinity()
                }
                .show()

            return
        }
    }
}

@Composable
fun DebugToolboxScreen() {

    val backgroundColor = Color(0xFF0D0D0D)
    val purple = Color(0xFF8E44AD)
    val lightPurple = Color(0xFFBB86FC)
    val descriptionColor = Color(0xFFAAAAAA)

    MaterialTheme {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 20.dp, vertical = 24.dp),
            verticalArrangement = Arrangement.Top
        ) {

            Text(
                text = "b1rd.ch4in",
                color = Color.White,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Device diagnostics and management tools",
                color = descriptionColor,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(28.dp))

            DebugButton(
                title = "Restart device",
                description = "Restart the device normally.",
                color = purple
            ) {
                DebugFunctions.reboot()
            }

            DebugButton(
                title = "Turns off device",
                description = "Turns off the device normally.",
                color = purple
            ) {
                DebugFunctions.shutdown()
            }

            DebugButton(
                title = "Force Device Reset",
                description = "Try to force the device to shut down.",
                color = purple
            ) {
                DebugFunctions.forceReset()
            }

            DebugButton(
                title = "Restart in Recovery Mode",
                description = "Restart the device in Recovery Mode",
                color = purple
            ) {
                DebugFunctions.rebootRecovery()
            }

            DebugButton(
                title = "Restart in Bootloader",
                description = "Restart the device in Bootloader/Fastboot.",
                color = purple
            ) {
                DebugFunctions.rebootBootloader()
            }

            DebugButton(
                title = "Open Settings",
                description = "Open the Android Settings app",
                color = purple
            ) {
                DebugFunctions.openSettings(this as Context)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Note: Some operations require elevated privileges, ADB, Shizuku, or root (superuser). " +
                        "By 85cs/Itelcan3 (aka. MasterSharp3210)",
                color = lightPurple,
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun DebugButton(
    title: String,
    description: String,
    color: Color,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 18.dp)
    ) {

        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = color,
                contentColor = Color.White
            )
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(7.dp))

        Text(
            text = description,
            color = Color(0xFFAAAAAA),
            fontSize = 13.sp,
            modifier = Modifier.padding(horizontal = 4.dp)
        )
    }
}
