package com.zy.mini_games.swipe

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.widget.GridLayout
import android.opengl.ETC1.getWidth
import android.widget.Toast
import com.zy.mini_games.R
import com.zy.mini_games.utils.showToast


/**
 * Created by Zhaoyue on 2018/8/22 0022.
 */
class SwipeGridLayout @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
        var x: Int = 9, var y: Int = 9, var mineNum: Int = 10//默认9行9列10雷
) : GridLayout(context, attrs, defStyleAttr) {


    init {
    }

    var started = false

    fun fillAll() {
        removeAllViews()
        rowCount = x
        columnCount = y
        for (j in 0 until x) {
            for (i in 0 until y) {
                val one = SingleView(context, i, j, this)
//                Log.i(TAG, "fillAll: i:" + i)
                addView(one, width / columnCount, height / rowCount)
            }
        }
    }

    fun onChildClicked(v: SingleView, x: Int, y: Int) {
        showToast("x:$x,y:$y")
        if (!started){
            initCurGame(x,y)
            started = true
        }
    }

    fun initCurGame(firstX:Int,firstY: Int){

    }

    fun onChildLongClicked(v: SingleView, x: Int, y: Int) {
        showToast("long_x:$x,y:$y")
    }

    fun getChildByIndex(x: Int, y: Int): SingleView {
        return getChildAt(y * columnCount + x) as SingleView
    }


    /**
     * 画分割线
     * https://blog.csdn.net/dodod2012/article/details/50955434
     */
    override fun dispatchDraw(canvas: Canvas) {
        super.dispatchDraw(canvas)
        if (childCount <= 0)
            return
        val localView1 = getChildAt(0)
        val column = columnCount
        val childCount = childCount
        val localPaint: Paint
        localPaint = Paint()
        localPaint.setStyle(Paint.Style.STROKE)
        localPaint.setColor(Color.BLACK)
        for (i in 0 until childCount) {
            val cellView = getChildAt(i)
//            if ((i + 1) % column == 0) {
//                canvas.drawLine(cellView.left.toFloat(), cellView.bottom.toFloat(),
//                        cellView.right.toFloat(), cellView.bottom.toFloat(), localPaint)
//            } else if (i + 1 > childCount - childCount % column) {
//                canvas.drawLine(cellView.right.toFloat(), cellView.top.toFloat(),
//                        cellView.right.toFloat(), cellView.bottom.toFloat(), localPaint)
//            } else {
            if (i < columnCount){
                canvas.drawLine(cellView.left.toFloat(), cellView.top.toFloat(),
                        cellView.right.toFloat(), cellView.top.toFloat(), localPaint)
            }
            if (i % columnCount == 0){
                canvas.drawLine(cellView.left.toFloat(), cellView.top.toFloat(),
                        cellView.left.toFloat(), cellView.bottom.toFloat(), localPaint)
            }
            canvas.drawLine(cellView.right.toFloat(), cellView.top.toFloat(),
                    cellView.right.toFloat(), cellView.bottom.toFloat(), localPaint)
            canvas.drawLine(cellView.left.toFloat(), cellView.bottom.toFloat(),
                    cellView.right.toFloat(), cellView.bottom.toFloat(), localPaint)
//            }
        }
        if (childCount % column != 0) {
            for (j in 0 until column - childCount % column) {
                val lastView = getChildAt(childCount - 1)
                canvas.drawLine((lastView.right + lastView.width * j).toFloat(), lastView.top.toFloat(),
                        (lastView.right + lastView.width * j).toFloat(), lastView.bottom.toFloat(), localPaint)
            }
        }
    }
}