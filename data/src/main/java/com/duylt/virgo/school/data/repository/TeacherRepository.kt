package com.duylt.virgo.school.data.repository

import com.duylt.virgo.school.data.remote.api.WebApiService
import com.duylt.virgo.school.data.remote.models.obj_res.TeacherResDto
import com.duylt.virgo.school.data.remote.models.obj_req.TeacherReqDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class TeacherRepository(
    private val webService: WebApiService,
) {

    val messageError500 =
        "Đã xảy ra lỗi trong quá trình xử lý yêu cầu. Vui lòng thử lại sau."

    suspend fun signUpAccountTeacher(
        teacherReq: TeacherReqDto
    ) =
        withContext(Dispatchers.IO) {
            val pairResult = signUpAccountTeacherResumeValue(teacherReq)
            return@withContext pairResult
        }

    private suspend fun signUpAccountTeacherResumeValue(
        teacherReq: TeacherReqDto
    ) =
        suspendCoroutine { continuation ->
            val responseSignUp =
                webService.signupTeacherAccount(teacherReq)
            responseSignUp.enqueue(object : Callback<TeacherResDto> {
                override fun onResponse(
                    call: Call<TeacherResDto>,
                    response: Response<TeacherResDto>
                ) {
                    val body = response.body()
                    body?.let {
                        continuation.resume(Pair(it.code, it.message))
                    } ?: {
                        continuation.resume(Pair(500, messageError500))
                    }
                }

                override fun onFailure(call: Call<TeacherResDto>, t: Throwable) {
                    continuation.resume(Pair(500, messageError500))
                }
            })
        }

    suspend fun loginAccountTeacher(
        teacherReq: TeacherReqDto
    ) =
        withContext(Dispatchers.IO) {
            val pairResult = loginAccountTeacherResumeValue(teacherReq)
            return@withContext pairResult
        }

    private suspend fun loginAccountTeacherResumeValue(
        teacherReq: TeacherReqDto
    ) =
        suspendCoroutine { continuation ->
            val response =
                webService.loginTeacherAccount(teacherReq)
            response.enqueue(object : Callback<TeacherResDto> {
                override fun onResponse(
                    call: Call<TeacherResDto>,
                    response: Response<TeacherResDto>
                ) {
                    val body = response.body()
                    body?.let {
                        continuation.resume(Pair(it.code, it.message))
                    } ?: {
                        continuation.resume(Pair(500, messageError500))
                    }
                }

                override fun onFailure(call: Call<TeacherResDto>, t: Throwable) {
                    continuation.resume(Pair(500, messageError500))
                }
            })
        }

}