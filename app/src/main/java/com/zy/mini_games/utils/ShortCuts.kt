package com.zy.mini_games.utils

import android.util.Log
import android.widget.Toast
import com.zy.mini_games.MyApplication

/**
 * Created by Zhaoyue on 2018/8/22 0022.
 */
const val TAG = "MiniGames"

fun showToast(string: String){
    Toast.makeText(MyApplication.context,string,Toast.LENGTH_SHORT).show()
}

fun showToast(resId:Int){
    Toast.makeText(MyApplication.context,resId,Toast.LENGTH_SHORT).show()
}

fun logi(string: String){
    Log.i(TAG,string)
}

fun loge(string: String){
    Log.e(TAG,string)
}