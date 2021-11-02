package com.example.multi.retrofitapi

import android.util.Log
import javax.inject.Inject

class MainRemoteData @Inject constructor() {
init {

    Log.wtf("MainREmoteData", "INJECTED")
}
    fun create(){
        Log.wtf("MainREmoteData", "created")
    }
}