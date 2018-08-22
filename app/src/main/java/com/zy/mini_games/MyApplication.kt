package com.zy.mini_games

import android.app.Application
import android.content.Context
import kotlin.properties.Delegates

/**
 * Created by Zhaoyue on 2018/8/22 0022.
 */
class MyApplication : Application() {
    companion object {
        private val TAG = "App"

        lateinit var instance:MyApplication

        var context: Context by Delegates.notNull()
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        context = applicationContext
    }
}