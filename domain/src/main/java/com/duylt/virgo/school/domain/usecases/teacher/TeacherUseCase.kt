package com.duylt.virgo.school.domain.usecases.teacher

import com.duylt.virgo.school.data.remote.models.obj_req.TeacherReqDto
import com.duylt.virgo.school.data.repository.TeacherRepository

class TeacherSignUpUseCase(
    private val teacherRepository: TeacherRepository
) {
    suspend operator fun invoke(
        teacherReqDto: TeacherReqDto
    ) =
        teacherRepository.signUpAccountTeacher(teacherReqDto)
}

class TeacherLoginUseCase(
    private val repository: TeacherRepository
) {
    suspend operator fun invoke(
        teacherReqDto: TeacherReqDto
    ) =
        repository.loginAccountTeacher(teacherReqDto)
}