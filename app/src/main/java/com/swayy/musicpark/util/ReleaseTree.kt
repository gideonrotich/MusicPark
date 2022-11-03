package com.swayy.musicpark.util

import timber.log.Timber
import android.util.Log
import org.jetbrains.annotations.NotNull

class ReleaseTree : @NotNull Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.ERROR || priority == Log.WARN){
            //SEND ERROR REPORTS TO YOUR Crashlytics.
        }
    }
}