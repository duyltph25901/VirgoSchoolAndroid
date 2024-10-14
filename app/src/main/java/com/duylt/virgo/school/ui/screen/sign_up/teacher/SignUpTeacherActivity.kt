package com.duylt.virgo.school.ui.screen.sign_up.teacher

import com.duylt.virgo.school.commons.ext.click
import com.duylt.virgo.school.databinding.ActivitySignUpTeacherBinding
import com.duylt.virgo.school.ui.base.BaseActivity

class SignUpTeacherActivity : BaseActivity<ActivitySignUpTeacherBinding>() {
    override fun inflateViewBinding(): ActivitySignUpTeacherBinding =
        ActivitySignUpTeacherBinding.inflate(layoutInflater)

    override fun clickViews() = binding.apply {
        textLogin.click { finish() }
    }
}