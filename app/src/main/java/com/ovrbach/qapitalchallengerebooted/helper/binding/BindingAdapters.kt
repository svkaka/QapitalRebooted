package com.ovrbach.qapitalchallengerebooted.helper.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ovrbach.qapitalchallengerebooted.domain.Response
import com.ovrbach.qapitalchallengerebooted.helper.hide
import com.ovrbach.qapitalchallengerebooted.helper.show

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String) {
    Glide.with(this).load(url).into(this)
}

@BindingAdapter("makeVisible")
fun View.makeVisible(show: Boolean) {
    if (show) {
        this.show()
    } else {
        this.hide()
    }
}

@BindingAdapter("progressVisibility")
fun View.progressVisibility(any: Response<Any>) {
    if (any is Response.Waiting) {
        show()
    } else {
        hide()
    }
}

@BindingAdapter("contentVisibility")
fun View.contentVisibility(any: Response<Any>) {
    if (any is Response.Success) {
        show()
    } else {
        hide()
    }
}

@BindingAdapter("errorVisibility")
fun View.errorVisibility(any: Response<Any>) {
    if (any is Response.Error) {
        show()
    } else {
        hide()
    }
}