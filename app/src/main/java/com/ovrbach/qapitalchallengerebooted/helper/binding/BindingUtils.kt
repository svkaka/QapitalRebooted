package com.ovrbach.qapitalchallengerebooted.helper.binding

import android.text.Spanned
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT

fun getProgress(current: Float, top: Float?): Int = if (top == null) 0 else (100 * current / top).toInt()

fun String.toSpanned(): Spanned? = HtmlCompat.fromHtml(this, FROM_HTML_MODE_COMPACT)