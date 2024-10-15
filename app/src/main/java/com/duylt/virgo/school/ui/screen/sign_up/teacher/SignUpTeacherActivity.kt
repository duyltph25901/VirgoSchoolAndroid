package com.duylt.virgo.school.ui.screen.sign_up.teacher

import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.duylt.virgo.school.commons.ext.click
import com.duylt.virgo.school.commons.ext.isNullInput
import com.duylt.virgo.school.commons.ext.validateEmail
import com.duylt.virgo.school.commons.ext.validatePassword
import com.duylt.virgo.school.commons.ext.validateUserName
import com.duylt.virgo.school.data.remote.RetrofitInstance
import com.duylt.virgo.school.data.remote.models.obj_req.TeacherReqDto
import com.duylt.virgo.school.data.repository.TeacherRepository
import com.duylt.virgo.school.databinding.ActivitySignUpTeacherBinding
import com.duylt.virgo.school.domain.usecases.teacher.TeacherSignUpUseCase
import com.duylt.virgo.school.ui.base.BaseActivity

class SignUpTeacherActivity : BaseActivity<ActivitySignUpTeacherBinding>() {

    private val viewModel: SignUpTeacherVm by lazy {
        ViewModelProvider(
            this, SignUpTeacherVmFac(
                TeacherSignUpUseCase(
                    TeacherRepository(RetrofitInstance.webService)
                )
            )
        )[SignUpTeacherVm::class.java]
    }

    override fun inflateViewBinding(): ActivitySignUpTeacherBinding =
        ActivitySignUpTeacherBinding.inflate(layoutInflater)

    override fun clickViews() = binding.apply {
        textLogin.click { finish() }

        btnCreateAnAccount.click { createdAccount() }
    }

    override fun observerDataSource() = viewModel.apply {
        isLoading.observe(this@SignUpTeacherActivity) { loading ->

        }
    }

    private fun createdAccount() {
        val teacherEmail = binding.edtEmail.text.toString().trim()
        val teacherName = binding.edtUseName.text.toString().trim()
        val teacherPassword = binding.edtPassword.text.toString().trim()

        if (!validateInput(teacherEmail, teacherName, teacherPassword)) {
            return
        }

        proceedToCreateAccount(TeacherReqDto(
            teacherEmail = teacherEmail,
            teacherUserName = teacherName,
            teacherPassword = teacherPassword
        ))
    }

    private fun validateInput(email: String, userName: String, password: String): Boolean {
        val isNullInput = isNullInput(email, userName, password)
        val isEmail = validateEmail(email)
        val isPassword = validatePassword(password)
        val isUserName = validateUserName(userName)

        if (isNullInput) {
            return false
        }

        if (!isEmail) {
            return false
        }

        if (!isPassword) {
            return false
        }

        if (!isUserName) {
            return false
        }

        return true
    }

    private fun proceedToCreateAccount(teacherReq: TeacherReqDto) {
        viewModel.signUpTeacherAccount(teacherReq)
        Log.d("hello_log", "Hi")
    }
}