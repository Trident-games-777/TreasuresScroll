package tv.sweet.playe.view

import android.animation.AnimatorSet
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.transition.ChangeBounds
import androidx.transition.Fade
import androidx.transition.Scene
import androidx.transition.TransitionManager
import tv.sweet.playe.R
import tv.sweet.playe.anim.ScaleAnimator
import tv.sweet.playe.anim.TransitionListener
import tv.sweet.playe.databinding.EndSceneBinding
import tv.sweet.playe.databinding.StartSceneBinding
import tv.sweet.playe.databinding.TreasureScrollBinding

class TreasureScroll : AppCompatActivity() {
    private lateinit var treasureBinding: TreasureScrollBinding
    private lateinit var startBinding: StartSceneBinding
    private lateinit var endBinding: EndSceneBinding

    private val treasures = listOf(
        R.drawable.treasure1,
        R.drawable.treasure2,
        R.drawable.treasure3,
        R.drawable.treasure4,
        R.drawable.treasure5
    )
    private var lines = mutableListOf<List<View>>()
    private var points = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        treasureBinding = TreasureScrollBinding.inflate(layoutInflater)
        startBinding = StartSceneBinding.inflate(layoutInflater)
        endBinding = EndSceneBinding.inflate(layoutInflater)
        setContentView(treasureBinding.root)

        setRandomTreasures()
        val sceneRoot = treasureBinding.sceneRoot
        val startScene = Scene(sceneRoot, startBinding.root)
        val endScene = Scene(sceneRoot, endBinding.root)

        treasureBinding.buttonScroll.setOnClickListener {
            treasureBinding.buttonScroll.isEnabled = false
            with(Fade()) {
                addListener(TransitionListener {
                    setRandomTreasures()
                    with(ChangeBounds()) {
                        addListener(TransitionListener {
                            if (lines.isNotEmpty()) {
                                val lineAnimators: List<AnimatorSet> =
                                    lines.map { ScaleAnimator.animator(*it.toTypedArray()) }
                                val tvPointsAnimator: AnimatorSet =
                                    ScaleAnimator.animator(treasureBinding.tvPoints)

                                ScaleAnimator.createAndStartAnimator(
                                    lastLine = lines.count(),
                                    lineAnimators = lineAnimators,
                                    tvPointsAnimator = tvPointsAnimator
                                ) { currentLine ->
                                    points += addPoints(lines[currentLine].count())
                                    treasureBinding.tvPoints.text = points.toString()
                                }
                            }
                            treasureBinding.buttonScroll.isEnabled = true
                        })
                        TransitionManager.go(endScene, this)
                    }
                })
                TransitionManager.go(startScene, this)
            }
        }
    }

    private fun setRandomTreasures() {
        val randomTreasures = List(5) { treasures.random() }
        val randomShuffledTreasures = randomTreasures.shuffled()
        endBinding.iv1.setImageResource(randomShuffledTreasures[0])
        endBinding.iv2.setImageResource(randomShuffledTreasures[1])
        endBinding.iv3.setImageResource(randomShuffledTreasures[2])
        endBinding.iv4.setImageResource(randomShuffledTreasures[3])
        endBinding.iv5.setImageResource(randomShuffledTreasures[4])

        startBinding.iv1.setImageResource(randomShuffledTreasures[0])
        startBinding.iv2.setImageResource(randomShuffledTreasures[1])
        startBinding.iv3.setImageResource(randomShuffledTreasures[2])
        startBinding.iv4.setImageResource(randomShuffledTreasures[3])
        startBinding.iv5.setImageResource(randomShuffledTreasures[4])

        var currentTreasure = randomShuffledTreasures.first()
        var count = 1

        fun addLine(process: () -> View) {
            if (count > 1) {
                lines.add(List(count) {
                    process()
                })
            }
        }

        lines.clear()
        for (index in 1 until randomShuffledTreasures.size) {
            if (randomShuffledTreasures[index] == currentTreasure) {
                count++
                if (index == randomShuffledTreasures.lastIndex) {
                    addLine {
                        viewFromIndex(index - (--count))
                    }
                }
            } else {
                currentTreasure = randomShuffledTreasures[index]
                addLine {
                    viewFromIndex(index - (count--))
                }
                count = 1
            }
        }
    }

    private fun viewFromIndex(index: Int): View {
        return when (index) {
            0 -> endBinding.iv1
            1 -> endBinding.iv2
            2 -> endBinding.iv3
            3 -> endBinding.iv4
            else -> endBinding.iv5
        }
    }

    private fun addPoints(count: Int): Int {
        return when (count) {
            2 -> 2
            3 -> 6
            4 -> 10
            5 -> 50
            else -> 0
        }
    }
}