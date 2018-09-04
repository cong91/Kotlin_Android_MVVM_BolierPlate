package {{ cookiecutter.package_name }}.ui.login

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.transition.Transition
import android.transition.TransitionInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AccelerateInterpolator
import {{ cookiecutter.package_name }}.R
import {{ cookiecutter.package_name }}.core.BaseActivity
import {{ cookiecutter.package_name }}.databinding.ActivityRegisterBinding

class RegisterActivity : BaseActivity<RegisterViewModel, ActivityRegisterBinding>(RegisterViewModel::class.java) {
    override fun getLayoutRes(): Int {
        return R.layout.activity_register
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showEnterAnimation()
        binding.fab.setOnClickListener { animateRevealClose() }
    }

    private fun showEnterAnimation() {
        val transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition)
        window.sharedElementEnterTransition = transition

        transition.addListener(object : Transition.TransitionListener {
            override fun onTransitionStart(transition: Transition) {
                binding.cvAdd.visibility = View.GONE
            }

            override fun onTransitionEnd(transition: Transition) {
                transition.removeListener(this)
                animateRevealShow()
            }

            override fun onTransitionCancel(transition: Transition) {

            }

            override fun onTransitionPause(transition: Transition) {

            }

            override fun onTransitionResume(transition: Transition) {

            }


        })
    }

    fun animateRevealShow() {
        val mAnimator = ViewAnimationUtils.createCircularReveal(binding.cvAdd, binding.cvAdd.width / 2, 0, (binding.fab.width / 2).toFloat(), binding.cvAdd.height.toFloat())
        mAnimator.duration = 500
        mAnimator.interpolator = AccelerateInterpolator()
        mAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
            }

            override fun onAnimationStart(animation: Animator) {
                binding.cvAdd.visibility = View.VISIBLE
                super.onAnimationStart(animation)
            }
        })
        mAnimator.start()
    }

    private var isClose: Boolean = false

    fun animateRevealClose() {
        if (!isClose)
            isClose = true
        val mAnimator = ViewAnimationUtils.createCircularReveal(binding.cvAdd, binding.cvAdd.width / 2, 0, binding.cvAdd.height.toFloat(), (binding.fab.width / 2).toFloat())
        mAnimator.duration = 500
        mAnimator.interpolator = AccelerateInterpolator()
        mAnimator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                binding.cvAdd.visibility = View.INVISIBLE
                super.onAnimationEnd(animation)
                binding.fab.setImageResource(R.drawable.plus)
                onBackPressed()
            }

            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
            }
        })
        mAnimator.start()
    }

    override fun onBackPressed() {
        if (isClose) {
            super.onBackPressed()
        } else {
            animateRevealClose()
        }
    }
}