package com.duylt.virgo.school.data.remote.api

import com.duylt.virgo.school.data.remote.models.obj_res.TeacherResDto
import com.duylt.virgo.school.data.remote.models.obj_req.TeacherReqDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface WebApiService {

    @POST("duylt/api/v1/teachers/account/login")
    fun loginTeacherAccount(@Body teacherReq: TeacherReqDto): Call<TeacherResDto>

    @POST("duylt/api/v1/teachers/account/signup")
    fun signupTeacherAccount(@Body teacherReq: TeacherReqDto): Call<TeacherResDto>

}