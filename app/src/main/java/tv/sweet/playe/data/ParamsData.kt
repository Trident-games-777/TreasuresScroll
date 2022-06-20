package tv.sweet.playe.data

import java.util.*

fun paramsMap(
    paramAf: MutableMap<String, Any>?,
    paramFb: String,
    paramGadId: String,
    paramAppsUID: String
) = mapOf<String, String>(
    "prysA1nhAi" to "Lm6IFsiu4h",
    "ngWX8P2z4h" to TimeZone.getDefault().id,
    "zb1ImetMrr" to paramGadId,
    "zJ2CWimEP5" to paramFb,
    "F5YGAHiI6r" to paramAf?.get("media_source").toString(),
    "Mbk3g84DnW" to paramAppsUID,
    "vlJlunjYHC" to paramAf?.get("adset_id").toString(),
    "PdgVefXTzb" to paramAf?.get("campaign_id").toString(),
    "aQCX4S46Q0" to paramAf?.get("campaign").toString(),
    "CHNigQzCE3" to paramAf?.get("adset").toString(),
    "CnE89wsOAl" to paramAf?.get("adgroup").toString(),
    "C1djVtu8aD" to paramAf?.get("orig_cost").toString(),
    "6lnUTVJf4Q" to paramAf?.get("af_siteid").toString()
)