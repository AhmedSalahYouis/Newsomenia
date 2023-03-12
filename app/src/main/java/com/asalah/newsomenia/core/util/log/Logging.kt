package com.asalah.newsomenia.core.util.log

import android.util.Log
import com.asalah.newsomenia.BuildConfig
import timber.log.Timber


fun showLog(tag: String, msg: String) {
    if (BuildConfig.DEBUG) {
        Timber.e(tag, msg)
    }
}

fun showLog(msg: String) {
    if (BuildConfig.DEBUG) {
        Timber.e("Test Log: ", msg)
    }
}