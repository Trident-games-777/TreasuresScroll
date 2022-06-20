package tv.sweet.playe.anim

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import androidx.core.animation.doOnEnd

class ScaleAnimator {
    companion object {
        private const val STANDARD_VIEW_SIZE = 1f
        private const val BIG_VIEW_SIZE = 1.4f

        fun animator(vararg views: View): AnimatorSet {
            val animators = List<Animator>(views.size) { index ->
                val currentView = views[index]
                val scaleUpX = ObjectAnimator.ofFloat(
                    currentView, "scaleX",
                    STANDARD_VIEW_SIZE, BIG_VIEW_SIZE
                )
                val scaleUpY = ObjectAnimator.ofFloat(
                    currentView, "scaleY",
                    STANDARD_VIEW_SIZE, BIG_VIEW_SIZE
                )
                val scaleDownX = ObjectAnimator.ofFloat(
                    currentView, "scaleX",
                    BIG_VIEW_SIZE, STANDARD_VIEW_SIZE
                )
                val scaleDownY = ObjectAnimator.ofFloat(
                    currentView, "scaleY",
                    BIG_VIEW_SIZE, STANDARD_VIEW_SIZE
                )
                val scaleUp = AnimatorSet().apply { playTogether(scaleUpX, scaleUpY) }
                val scaleDown = AnimatorSet().apply { playTogether(scaleDownX, scaleDownY) }
                AnimatorSet().apply { playSequentially(scaleUp, scaleDown) }
            }
            return AnimatorSet().apply {
                playTogether(animators)
            }
        }

        fun createAndStartAnimator(
            currentLine: Int = 0,
            lastLine: Int,
            lineAnimators: List<AnimatorSet>,
            tvPointsAnimator: AnimatorSet,
            calculatePoints: (Int) -> Unit
        ) {
            if (currentLine < lastLine) {
                AnimatorSet().apply {
                    playTogether(lineAnimators[currentLine], tvPointsAnimator)
                    doOnEnd {
                        createAndStartAnimator(
                            currentLine = currentLine + 1,
                            lastLine = lastLine,
                            lineAnimators = lineAnimators,
                            tvPointsAnimator = tvPointsAnimator,
                            calculatePoints = calculatePoints
                        )
                    }
                }.start().also { calculatePoints(currentLine) }
            }
        }
    }
}