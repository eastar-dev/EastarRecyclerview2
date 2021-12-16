package dev.eastar.recyclerview.model

import android.log.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("srcUrl")
fun ImageView.setImageUrl(url: String?) {
    Log.e(url)
    kotlin.runCatching {
        Glide.with(this).load(url).into(this)
    }.getOrNull()
}


