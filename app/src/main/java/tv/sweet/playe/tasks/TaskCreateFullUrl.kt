package tv.sweet.playe.tasks

import tv.sweet.playe.data.INIT_DATA
import tv.sweet.playe.extensions.toCompleteUrl

object TaskCreateFullUrl {
    fun execute(
        paramAf: MutableMap<String, Any>?,
        paramFb: String,
        paramGadId: String,
        paramAppsUID: String
    ): String = INIT_DATA.toCompleteUrl(paramAf, paramFb, paramGadId, paramAppsUID)
}