package com.duylt.virgo.school.data.remote.models.obj_res

import com.google.gson.annotations.SerializedName

data class TeacherResDto(
    @SerializedName("message")
    var message: String = "",
    @SerializedName("code")
    var code: Int = 200,
    @SerializedName("obj")
    var obj: Any? = null
) {
}