package tv.sweet.playe.extensions

import android.app.Activity
import android.content.Intent
import tv.sweet.playe.view.TreasureActivityWebView
import tv.sweet.playe.view.TreasureScroll

fun Activity.treasureScroll() {
    startActivity(Intent(this, TreasureScroll::class.java)).also { finish() }
}

fun Activity.treasureWeb(data: String) {
    val intent = Intent(this, TreasureActivityWebView::class.java)
    intent.putExtra("data", data)
    startActivity(intent)
    finish()
}