package com.zy.mini_games

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.zy.mini_games.base.BaseActivity
import com.zy.mini_games.swipe.SwipeActivity

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun swipe(v:View){
        startActivity(Intent(this,SwipeActivity::class.java))
    }
}
