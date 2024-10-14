package com.duylt.virgo.school.ui.screen.splash

import android.annotation.SuppressLint
import android.content.Intent
import com.duylt.virgo.school.commons.SharedUtils
import com.duylt.virgo.school.databinding.ActivitySplashBinding
import com.duylt.virgo.school.ui.base.BaseActivity
import com.duylt.virgo.school.ui.screen.language.LanguageActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    private var timeOutJob = CoroutineScope(Dispatchers.Main)
    private var timeOut = 5000

    override fun inflateViewBinding(): ActivitySplashBinding =
        ActivitySplashBinding.inflate(layoutInflater)

    override fun initVariable() {
        SharedUtils.init(this)
        binding.progressBar.max = timeOut
        timeOutJob.launch {
            var time = 0
            if (isActive) {
                while (time < timeOut) {
                    delay(1)
                    time++
                    binding.progressBar.progress = time
                }
            }
            openLanguageScreen()
        }
    }

    private fun openLanguageScreen() {
        startActivity(Intent(this, LanguageActivity::class.java))
        finish()
    }
}