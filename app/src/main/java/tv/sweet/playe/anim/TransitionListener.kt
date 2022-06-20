package tv.sweet.playe.anim

import androidx.transition.Transition

class TransitionListener(private val block: () -> Unit) : Transition.TransitionListener {
    override fun onTransitionStart(transition: Transition) {}
    override fun onTransitionCancel(transition: Transition) {}
    override fun onTransitionPause(transition: Transition) {}
    override fun onTransitionResume(transition: Transition) {}
    override fun onTransitionEnd(transition: Transition) {
        block()
    }
}