package tv.sweet.playe.tasks

import android.content.Context
import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object TaskObtainAppsFlyer {
    suspend fun obtain(context: Context): MutableMap<String, Any>? = suspendCoroutine {
        AppsFlyerLib.getInstance()
            .registerConversionListener(context, object : AppsFlyerConversionListener {
                override fun onConversionDataSuccess(p0: MutableMap<String, Any>?) {
                    it.resume(p0)
                }

                override fun onConversionDataFail(p0: String?) {
                }

                override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {
                }

                override fun onAttributionFailure(p0: String?) {
                }
            })
        AppsFlyerLib.getInstance().start(context)
    }
}