package com.duylt.virgo.school.ui.screen.sign_up.teacher

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.duylt.virgo.school.data.remote.models.obj_req.TeacherReqDto
import com.duylt.virgo.school.domain.usecases.teacher.TeacherSignUpUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpTeacherVm(
    private val teacherSignUpUseCase: TeacherSignUpUseCase,
) : ViewModel() {

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    val isLoading: LiveData<Boolean> = _isLoading

    fun signUpTeacherAccount(teacherReq: TeacherReqDto) =
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val res = teacherSignUpUseCase.invoke(teacherReq)
            Log.d("hello_log", "Code: ${res.first}\nMessage: ${res.second}")
            _isLoading.postValue(false)
        }

}