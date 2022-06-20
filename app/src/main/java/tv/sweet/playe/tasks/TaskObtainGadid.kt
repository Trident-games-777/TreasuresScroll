package tv.sweet.playe.tasks

import android.content.Context
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object TaskObtainGadid {
    @Suppress("BlockingMethodInNonBlockingContext")
    suspend fun obtain(context: Context): String = withContext(Dispatchers.Default) {
        AdvertisingIdClient.getAdvertisingIdInfo(context).id.toString()
    }
}