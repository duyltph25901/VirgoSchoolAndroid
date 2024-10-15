package com.duylt.virgo.school.ui.screen.sign_up.teacher

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.duylt.virgo.school.domain.usecases.teacher.TeacherSignUpUseCase

class SignUpTeacherVmFac(
    private val teacherSignUpUseCase: TeacherSignUpUseCase
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SignUpTeacherVm::class.java)) {
            return SignUpTeacherVm(teacherSignUpUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}