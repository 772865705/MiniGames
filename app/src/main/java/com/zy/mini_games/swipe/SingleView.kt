package com.zy.mini_games.swipe

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import com.zy.mini_games.R

/**
 * Created by Zhaoyue on 2018/8/21 0021.
 * 扫雷单个方块
 */
class SingleView(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
    : ImageView(context, attrs, defStyleAttr), View.OnClickListener, View.OnLongClickListener {

    companion object {
        //常量定义
        const val VIEWSTATE_DEFAULT = 0
        const val VIEWSTATE_FLAG = 1//标记这里是雷
        const val VIEWSTATE_UNSURE = 2//？ 不确定
        const val VIEWSTATE_TIP = 3//显示周围雷数 需要配合around参数
        const val VIEWSTATE_THUNDER = 4//游戏结束的时候显示这里的确是雷
        const val VIEWSTATE_BOMB = 5//游戏失败时最后点到的雷

        const val LOGICSTATE_NOT_INITED = -1
        const val LOGICSTATE_BOMB = -2
    }

    //    val mPaint = Paint()
    var around = 0//周围有几颗雷
        set(value) {
            if (value > 8)
                throw IllegalArgumentException("around should not be this value:$value")
            else
                field = value
        }
    var viewState = 0
    var logicState = -1//-1 未初始化 0~8周围地雷数 -2雷 只有这几种可能
    var enable = true//点击过以后不能再次点击 或者也有可能设置不可点击就行了？
    var x = -1
    var y = -1
    lateinit var mGrid: SwipeGridLayout


    init {
        isClickable = true
        setOnClickListener(this)
        setOnLongClickListener(this)
        setBackgroundResource(R.drawable.sel_bg_single)
        scaleType = ScaleType.CENTER_CROP

        updateInsideImg()
        if (attrs != null) {
            //nothing to do
        }
    }

    constructor(context: Context?, x: Int, y: Int, mGridLayout: SwipeGridLayout) : this(context) {
        this.x = x
        this.y = y
        this.mGrid = mGridLayout
    }

    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?) : this(context, null)

    fun updateInsideImg(): Unit {
        setImageResource(
                when (viewState) {
                    VIEWSTATE_DEFAULT -> R.mipmap.s
                    VIEWSTATE_BOMB -> R.mipmap.bomb0
                    VIEWSTATE_THUNDER -> R.mipmap.bomb
                    VIEWSTATE_FLAG -> R.mipmap.flag
                    VIEWSTATE_UNSURE -> R.mipmap.flag2
                    VIEWSTATE_TIP -> when (around) {
                        0 -> R.mipmap.ar0
                        1 -> R.mipmap.ar1
                        2 -> R.mipmap.ar2
                        3 -> R.mipmap.ar3
                        4 -> R.mipmap.ar4
                        5 -> R.mipmap.ar5
                        6 -> R.mipmap.ar6
                        7 -> R.mipmap.ar7
                        8 -> R.mipmap.ar8
                        else -> throw IllegalArgumentException("illegal around")
                    }
                    else -> throw IllegalArgumentException("illegal viewState")
                }
        )
        invalidate()//理论上应该都是在主线程调用的该方法
    }

    override fun onClick(v: View?) {
        if (!enable)
            return

        mGrid.onChildClicked(this,x, y)
    }

    override fun onLongClick(v: View?): Boolean {
        if (!enable)
            return true

        mGrid.onChildLongClicked(this,x,y)
        return true
    }

    fun reset() {

    }
}


