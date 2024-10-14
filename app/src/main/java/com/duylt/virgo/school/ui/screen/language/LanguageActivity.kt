package com.duylt.virgo.school.ui.screen.language

import android.content.Intent
import androidx.activity.viewModels
import com.duylt.virgo.school.R
import com.duylt.virgo.school.commons.SharedUtils
import com.duylt.virgo.school.commons.ext.click
import com.duylt.virgo.school.databinding.ActivityLanguageBinding
import com.duylt.virgo.school.model.LanguageModel
import com.duylt.virgo.school.ui.adapter.LanguageAdapter
import com.duylt.virgo.school.ui.base.BaseActivity
import com.duylt.virgo.school.ui.screen.onboarding.OnboardingActivity

class LanguageActivity : BaseActivity<ActivityLanguageBinding>() {

    private lateinit var languageAdapter: LanguageAdapter

    private val viewModel: LanguageViewModel by viewModels()

    private var language: LanguageModel? = null

    override fun inflateViewBinding(): ActivityLanguageBinding =
        ActivityLanguageBinding.inflate(layoutInflater)

    override fun initVariable() {
        viewModel.fetchLanguage()
        initLanguageAdapter()
    }

    override fun observerDataSource() = viewModel.apply {
        languages.observe(this@LanguageActivity) {
            languageAdapter.submitData(it)
        }
    }

    override fun clickViews() = binding.apply {
        btnDone.click {
            val canClick = languageAdapter.indexSelected != -1 && language != null
            if (canClick) {
                SharedUtils.languageCode = language!!.isoLanguage
                startActivity(Intent(this@LanguageActivity, OnboardingActivity::class.java))
                finish()
            }
        }
    }

    private fun initLanguageAdapter() = binding.rcvLanguage.apply {
        languageAdapter =
            LanguageAdapter(this@LanguageActivity, onLanguageSelected = { language, index ->
                val indexSelectedBefore = languageAdapter.indexSelected
                languageAdapter.indexSelected = index
                languageAdapter.notifyItemChanged(indexSelectedBefore)
                binding.btnDone.setBackgroundResource(R.drawable.bg_btn_done_language_active)
                this@LanguageActivity.language = language
            })

        adapter = languageAdapter
    }
}