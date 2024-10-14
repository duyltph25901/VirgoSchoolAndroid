package com.duylt.virgo.school.ui.screen.onboarding

import android.annotation.SuppressLint
import android.content.Intent
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.duylt.virgo.school.R
import com.duylt.virgo.school.commons.SharedUtils.jsonAccountStudent
import com.duylt.virgo.school.commons.SharedUtils.jsonAccountTeacher
import com.duylt.virgo.school.commons.ext.click
import com.duylt.virgo.school.databinding.ActivityOnboardingBinding
import com.duylt.virgo.school.ui.adapter.OnboardingAdapter
import com.duylt.virgo.school.ui.base.BaseActivity
import com.duylt.virgo.school.ui.dialog.ChooseRoleDialog
import com.duylt.virgo.school.ui.screen.home.student.HomeStudentActivity
import com.duylt.virgo.school.ui.screen.login.teacher.LoginTeacherActivity
import kotlin.math.abs

class OnboardingActivity : BaseActivity<ActivityOnboardingBinding>() {

    private lateinit var onboardingAdapter: OnboardingAdapter
    private lateinit var compositePageTransformer: CompositePageTransformer

    private val viewModel: OnboardingViewModel by viewModels()

    override fun inflateViewBinding(): ActivityOnboardingBinding =
        ActivityOnboardingBinding.inflate(layoutInflater)

    override fun initVariable() {
        initOnboardingAdapter()
        viewModel.fetchDataIntro()
    }

    override fun observerDataSource() {
        viewModel.apply {
            introList.observe(this@OnboardingActivity) {
                onboardingAdapter.submitData(it)
            }
        }
    }

    override fun clickViews() = binding.apply {
        buttonNext.click { nextOnboarding() }
    }

    private fun initOnboardingAdapter() = binding.vpgOnboarding.apply {
        onboardingAdapter = OnboardingAdapter(this@OnboardingActivity)
        compositePageTransformer = CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(100))
            addTransformer { view, position ->
                val r = 1 - abs(position)
                view.scaleY = 0.8f + r * 0.2f
                val absPosition = abs(position)
                view.alpha = 1.0f - (1.0f - 0.3f) * absPosition
            }
        }

        adapter = onboardingAdapter
        clipToPadding = false
        clipChildren = false
        offscreenPageLimit = 3
        getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_ALWAYS
        setPageTransformer(compositePageTransformer)
        registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            @SuppressLint("InvalidAnalyticsName", "UseCompatLoadingForDrawables")
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)

                when (position) {
                    0 -> {
                        binding.indicator1.setBackgroundResource(R.drawable.bg_indicator_active)
                        binding.indicator2.setBackgroundResource(R.drawable.bg_indicator_un_active)
                        binding.indicator3.setBackgroundResource(R.drawable.bg_indicator_un_active)
                    }

                    1 -> {
                        binding.indicator1.setBackgroundResource(R.drawable.bg_indicator_un_active)
                        binding.indicator2.setBackgroundResource(R.drawable.bg_indicator_active)
                        binding.indicator3.setBackgroundResource(R.drawable.bg_indicator_un_active)
                    }

                    2 -> {
                        binding.indicator1.setBackgroundResource(R.drawable.bg_indicator_un_active)
                        binding.indicator2.setBackgroundResource(R.drawable.bg_indicator_un_active)
                        binding.indicator3.setBackgroundResource(R.drawable.bg_indicator_active)
                    }
                }
            }
        })
    }

    private fun nextOnboarding() {
        when (val currentPosition = binding.vpgOnboarding.currentItem) {
            2 -> {
                val jsonAccTeacher = jsonAccountTeacher
                val jsonAccStudent = jsonAccountStudent
                val isNotLogin = jsonAccTeacher.isEmpty() && jsonAccStudent.isEmpty()
                if (isNotLogin) {
                    ChooseRoleDialog(context = this, onRoleStudentSelected = {
                        startActivity(Intent(this, HomeStudentActivity::class.java))
                        finish()
                    }, onRoleTeacherSelected = {
                        startActivity(Intent(this, LoginTeacherActivity::class.java))
                        finish()
                    }).show()
                }
            }

            else -> {
                binding.vpgOnboarding.currentItem = currentPosition + 1
            }
        }
    }
}