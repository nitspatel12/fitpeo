package com.fitpeo.assignment.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageFromUrl")
fun ImageView.imageFromUrl(url:String){
    Picasso.get().load(url).into(this);
}