package com.duylt.virgo.school

import android.app.Application
import com.duylt.virgo.school.commons.SharedUtils

class GlobalApp: Application() {

    override fun onCreate() {
        super.onCreate()

        SharedUtils.init(this)
    }

}