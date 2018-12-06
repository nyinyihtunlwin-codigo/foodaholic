package com.nyinyihtunlwin.projects.foodaholic.activities

import agency.tango.materialintroscreen.MaterialIntroActivity
import agency.tango.materialintroscreen.SlideFragmentBuilder
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.nyinyihtunlwin.projects.foodaholic.R


class IntroScreenActivity : MaterialIntroActivity() {

    companion object {
        fun newInstnace(context: Context): Intent {
            return Intent(context, IntroScreenActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableLastSlideAlphaExitTransition(true)

        backButtonTranslationWrapper
            .setEnterTranslation { view, percentage -> view.alpha = percentage }

        addSlide(
            SlideFragmentBuilder()
                .backgroundColor(R.color.white)
                .buttonsColor(R.color.accent)
                .image(R.drawable.desert)
                .title("Desert")
                .description("The most you ever ate?")
                .build()
        )
        addSlide(
            SlideFragmentBuilder()
                .backgroundColor(R.color.white)
                .buttonsColor(R.color.accent)
                .image(R.drawable.miscellaneous)
                .title("Miscellaneous")
                .description("Miscellaneous prepared food?")
                .build()
        )
        addSlide(
            SlideFragmentBuilder()
                .backgroundColor(R.color.white)
                .buttonsColor(R.color.accent)
                .image(R.drawable.side)
                .title("Side")
                .description("Would you join us?")
                .build()
        )
    }

    override fun onFinish() {
        super.onFinish()
    }

}