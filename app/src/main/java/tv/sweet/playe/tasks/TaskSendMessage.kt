package tv.sweet.playe.tasks

import com.onesignal.OneSignal

object TaskSendMessage {
    fun execute(
        paramAf: MutableMap<String, Any>?,
        paramFb: String
    ) {
        val campaign = paramAf?.get("campaign").toString()

        if (campaign == "null" && paramFb == "null") {
            OneSignal.sendTag("key2", "organic")
        } else if (paramFb != "null") {
            OneSignal.sendTag(
                "key2", paramFb.replace("myapp://", "")
                    .substringBefore("/")
            )
        } else if (campaign != "null") {
            OneSignal.sendTag("key2", campaign.substringBefore("_"))
        }
    }
}