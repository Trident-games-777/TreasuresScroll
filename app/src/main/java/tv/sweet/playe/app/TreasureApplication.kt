package tv.sweet.playe.app

import android.app.Application
import com.appsflyer.AppsFlyerLib
import com.onesignal.OneSignal
import tv.sweet.playe.data.APPS_DEV_KEY
import tv.sweet.playe.data.ONE_SIGNAL_ID

class TreasureApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONE_SIGNAL_ID)
        AppsFlyerLib.getInstance().init(APPS_DEV_KEY, null, this)
    }
}