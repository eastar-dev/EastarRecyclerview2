package dev.eastar.recyclerview.model

import android.log.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("glideImage")
fun setGlideLoad(
    view: ImageView,
    data: String?,
) {
    Log.e(view, data)
    runCatching {
        Glide.with(view).load(data).into(view)
    }
}


