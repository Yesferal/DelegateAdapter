package com.yesferal.hornsapp.delegate.util

import android.content.Context

internal fun convertDpToPixel(dp: Float, context: Context): Int {
    val density = context.resources.displayMetrics.density
    return (density * dp).toInt()
}
