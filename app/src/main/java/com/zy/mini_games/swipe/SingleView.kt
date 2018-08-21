package com.zy.mini_games.swipe

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import com.zy.mini_games.R

/**
 * Created by Zhaoyue on 2018/8/21 0021.
 * 扫雷单个视图
 */
class SingleView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
    : ImageView(context, attrs, defStyleAttr),View.OnClickListener,View.OnLongClickListener {

    val STATE_DEFAULT = 0
    val STATE_FLAG = 1//标记这里是雷
    val STATE_UNSURE = 2//？ 不确定
    val STATE_TIP = 3//显示周围雷数 需要配合around参数
    val STATE_THUNDER = 4//游戏结束的时候显示这里的确是雷
    val STATE_BOMB = 5//游戏失败时最后点到的雷

//    val mPaint = Paint()
    var around = 0//周围有几颗累
    var state = 0
    var clicked = false//点击过以后不能再次点击 或者也有可能设置不可点击就行了？

    init {
        isClickable = true
        setOnClickListener(this)
        setOnLongClickListener(this)
        setBackgroundResource(R.drawable.sel_bg_single)
        scaleType = ScaleType.CENTER_CROP

        if (attrs != null){
            //nothing to do
        }
    }

    constructor(context: Context?, attrs: AttributeSet?):this(context, attrs,0)
    constructor(context: Context?):this(context,null)

    fun getInsideImg():Int{
        return when(state){
            STATE_DEFAULT -> R.mipmap.s
            STATE_BOMB -> R.mipmap.bomb0
            STATE_THUNDER -> R.mipmap.bomb
            STATE_FLAG -> R.mipmap.flag
            STATE_UNSURE -> R.mipmap.flag2
            STATE_TIP -> when(around){
                0 ->R.mipmap.ar0
                1 ->R.mipmap.ar1
                2->R.mipmap.ar2
                3 ->R.mipmap.ar3
                4 ->R.mipmap.ar4
                5 ->R.mipmap.ar5
                6 ->R.mipmap.ar6
                7 ->R.mipmap.ar7
                8 ->R.mipmap.ar8
                else -> throw IllegalArgumentException("illegal around")
            }
            else -> throw IllegalArgumentException("illegal state")
        }
    }

    override fun onClick(v: View?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLongClick(v: View?): Boolean {

        return true
    }
}