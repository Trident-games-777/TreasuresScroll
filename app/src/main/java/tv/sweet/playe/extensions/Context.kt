package tv.sweet.playe.extensions

import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings
import com.onesignal.OneSignal
import java.io.File

val Context.developer: Boolean
    get() {
        return Settings.Secure.getInt(
            contentResolver,
            Settings.Global.DEVELOPMENT_SETTINGS_ENABLED,
            0
        ) != 0
    }

val Context.rooted: Boolean
    get() {
        var result = false
        try {
            if (File("/sbin/su").exists()) result = true
            if (File("/system/bin/su").exists()) result = true
            if (File("/system/xbin/su").exists()) result = true
            if (File("/data/local/xbin/su").exists()) result = true
            if (File("/data/local/bin/su").exists()) result = true
            if (File("/system/sd/xbin/su").exists()) result = true
            if (File("/system/bin/failsafe/su").exists()) result = true
            if (File("/data/local/su").exists()) result = true
        } catch (e: Exception) {
            result = false
        }
        return result
    }

val Context.storage: SharedPreferences
    get() = getSharedPreferences("storage", Context.MODE_PRIVATE)