package tv.sweet.playe.extensions

import androidx.core.net.toUri
import tv.sweet.playe.data.paramsMap

fun String.toCompleteUrl(
    paramAf: MutableMap<String, Any>?,
    paramFb: String,
    paramGadId: String,
    paramAppsUID: String
): String {
    val paramsMap = paramsMap(paramAf, paramFb, paramGadId, paramAppsUID)
    return toUri().buildUpon().apply {
        paramsMap.forEach { (key, value) ->
            appendQueryParameter(key, value)
        }
    }.toString()
}