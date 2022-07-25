package com.example.marsproperty

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val type = intent.getStringExtra("type")
        val price = intent.getStringExtra("price")
        val img_src = intent.getStringExtra("img_src").toString()
        property_rent.text = "Price:- ${price.toString()}"
        property_type.text = "Type:-${type.toString()}"
        Glide.with(this).load(img_src).into(findViewById(R.id.mars_image))

    }
}