package ir.reza_mahmoudi.imovie.utils

import android.util.Log
import ir.reza_mahmoudi.imovie.BuildConfig


fun showLog(tag: String, msg: String){
    if(BuildConfig.DEBUG){
        Log.e(tag,msg)
    }
}