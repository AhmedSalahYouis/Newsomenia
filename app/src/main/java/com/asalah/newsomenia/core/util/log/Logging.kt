package com.asalah.newsomenia.core.util.log

import android.util.Log
import com.asalah.newsomenia.BuildConfig


fun showLog(tag: String, msg: String) {
    if (BuildConfig.DEBUG) {
        Log.e(tag, msg)
    }
}

fun showLog(msg: String) {
    if (BuildConfig.DEBUG) {
        Log.e("Test Log: ", msg)
    }
}