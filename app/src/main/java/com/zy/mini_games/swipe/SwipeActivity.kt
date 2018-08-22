package com.zy.mini_games.swipe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.zy.mini_games.R
import com.zy.mini_games.base.BaseActivity
import kotlinx.android.synthetic.main.activity_swipe.*

class SwipeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe)
        grid_swipe.postDelayed({
            grid_swipe.fillAll()
        },120)
    }
}
