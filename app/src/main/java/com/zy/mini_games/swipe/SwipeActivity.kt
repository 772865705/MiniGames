package com.zy.mini_games.swipe

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.zy.mini_games.R
import com.zy.mini_games.base.BaseActivity
import com.zy.mini_games.utils.showToast
import kotlinx.android.synthetic.main.activity_swipe.*
import java.util.*

class SwipeActivity : BaseActivity() {

    val timer:Timer = Timer()
    var task:TimerTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swipe)
        grid_swipe.postDelayed({
            grid_swipe.fillAll()
        },120)

        btn_start.setOnClickListener {
            grid_swipe.reset()
            tv_rest.setText(LogicHelper.int2String(grid_swipe.mineNum))
            img_endgame.visibility = View.GONE
            tv_time.setText("000")
            task?.cancel()
        }
        img_endgame.setOnClickListener { img_endgame.visibility = View.GONE }
        tv_rest.setText(LogicHelper.int2String(grid_swipe.mineNum))
        val lcdFont = Typeface.createFromAsset(assets, "fonts/lcd2mono.ttf")
        tv_rest.setTypeface(lcdFont)
        tv_time.setTypeface(lcdFont)
        grid_swipe.listener = object :SwipeGridLayout.GameStateChange{
            override fun onStart(rest:Int) {
//                showToast("开始")
                tv_rest.setText(LogicHelper.int2String(rest))
                task?.cancel()
                task = object :TimerTask(){
                    override fun run() {
                        runOnUiThread {
                            val t = tv_time.text.toString().toInt()+1
                            tv_time.setText(LogicHelper.int2String(t))
                        }
                    }
                }
                timer.scheduleAtFixedRate(task,0,1000)
            }

            override fun onFlagsChange(flags: Int, rest: Int) {
                tv_rest.setText(LogicHelper.int2String(rest))
            }

            override fun victory() {
//                showToast("胜利")
                img_endgame.setImageResource(R.drawable.victory)
                img_endgame.visibility = View.VISIBLE
                task?.cancel()
//                tv_time.setText("000")
            }

            override fun defeat() {
//                showToast("失败")
                img_endgame.setImageResource(R.drawable.defeat)
                img_endgame.visibility = View.VISIBLE
                task?.cancel()
//                tv_time.setText("000")
            }

        }


    }
}
