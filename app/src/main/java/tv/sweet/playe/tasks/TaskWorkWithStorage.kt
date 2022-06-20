package tv.sweet.playe.tasks

import android.content.Context
import tv.sweet.playe.data.INIT_DATA
import tv.sweet.playe.extensions.storage

object TaskWorkWithStorage {
    private const val URL_KEY = "url"
    fun get(context: Context): String {
        return context.storage.getString(URL_KEY, INIT_DATA)!!
    }

    fun put(context: Context, data: String) {
        if (!isCompletedData(context)) {
            with(context.storage.edit()) {
                putString(URL_KEY, data)
                apply()
            }
        }
    }

    fun isCompletedData(context: Context): Boolean {
        return !get(context).contains(INIT_DATA)
    }
}