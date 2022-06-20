package tv.sweet.playe.tasks

import android.content.Context
import com.facebook.applinks.AppLinkData
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object TaskObtainAppLinkData {
    suspend fun obtain(context: Context): String = suspendCoroutine {
        AppLinkData.fetchDeferredAppLinkData(context) { appLinkData ->
            it.resume(appLinkData?.targetUri.toString())
        }
    }
}