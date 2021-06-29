package com.example.popularnews.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("imageUrl")
fun bindImage(imageView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        Glide.with(imageView.context)
            .load(imgUrl)
            .into(imageView)
    }
}

@BindingAdapter("showDate")
fun bidingDate(textView: TextView, date: String) {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val outputFormat = SimpleDateFormat("E, dd MMM  yyyy")
    val date: Date = inputFormat.parse(date)
    val formattedDate: String = outputFormat.format(date)
    textView.text = formattedDate
}
