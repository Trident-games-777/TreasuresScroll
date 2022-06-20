package tv.sweet.playe.tasks

import android.content.Context
import com.appsflyer.AppsFlyerLib

object TaskObtainAppsUID {
    fun obtain(context: Context): String = AppsFlyerLib.getInstance().getAppsFlyerUID(context)
}