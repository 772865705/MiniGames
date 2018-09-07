package com.zy.mini_games.swipe

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.zy.mini_games.R
import com.zy.mini_games.base.BaseActivity
import com.zy.mini_games.utils.showToast
import kotlinx.android.synthetic.main.activity_swipe.*

class SwipeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe)
        grid_swipe.postDelayed({
            grid_swipe.fillAll()
        },120)

        btn_start.setOnClickListener {
            grid_swipe.reset()
            tv_rest.setText("剩余:${grid_swipe.mineNum} 颗雷")
            img_endgame.visibility = View.GONE
        }
        tv_rest.setText("剩余:${grid_swipe.mineNum} 颗雷")
        grid_swipe.listener = object :SwipeGridLayout.GameStateChange{
            override fun onStart(rest:Int) {
                showToast("开始")
                tv_rest.setText("剩余:$rest 颗雷")
            }

            override fun onFlagsChange(flags: Int, rest: Int) {
                tv_rest.setText("剩余:$rest 颗雷")
            }

            override fun victory() {
                showToast("胜利")
                img_endgame.setImageResource(R.drawable.victory)
                img_endgame.visibility = View.VISIBLE
            }

            override fun defeat() {
                showToast("失败")
                img_endgame.setImageResource(R.drawable.defeat)
                img_endgame.visibility = View.VISIBLE
            }

        }
    }
}
