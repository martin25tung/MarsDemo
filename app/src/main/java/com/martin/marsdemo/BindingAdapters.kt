package com.martin.marsdemo

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


// 6. 新增能解析 imgUrl 字串成 URI 的 BindingAdapter
// 使用 data binding 讓 xml 用 BindingAdapter 解析 imgUrl
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = it.toUri().buildUpon().scheme("https").build()
        // 7. 用 Glide 來 load 圖
        Glide.with(imgView.context)
            .load(imgUri)
            // 11. 用 RequestOptions 來加上 loading 或 error 的圖片
            .apply(RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}