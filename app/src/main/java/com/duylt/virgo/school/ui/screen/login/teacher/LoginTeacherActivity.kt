package com.duylt.virgo.school.ui.screen.login.teacher

import android.content.Intent
import com.duylt.virgo.school.commons.ext.click
import com.duylt.virgo.school.databinding.ActivityLoginTeacherBinding
import com.duylt.virgo.school.ui.base.BaseActivity
import com.duylt.virgo.school.ui.screen.sign_up.teacher.SignUpTeacherActivity

class LoginTeacherActivity : BaseActivity<ActivityLoginTeacherBinding>() {
    override fun inflateViewBinding(): ActivityLoginTeacherBinding =
        ActivityLoginTeacherBinding.inflate(layoutInflater)

    override fun clickViews() = binding.apply {
        btnCreateAnAccount.click {
            startActivity(Intent(this@LoginTeacherActivity, SignUpTeacherActivity::class.java))
        }
    }
}