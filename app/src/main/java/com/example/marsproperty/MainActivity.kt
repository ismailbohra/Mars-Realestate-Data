package com.example.marsproperty

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    lateinit var adapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel:MarsViewModel = ViewModelProvider(this).get(MarsViewModel::class.java)
        viewModel.properties.observe(this, Observer{
            adapter = RecyclerViewAdapter(this@MainActivity, it)
            mars_property_recyclerView.adapter = adapter
            mars_property_recyclerView.layoutManager  = LinearLayoutManager(this@MainActivity)
            adapter.setOnItemClickListener(object:RecyclerViewAdapter.onItemClickListener{
                override fun onItemClick(position: Int) {
                    //Toast.makeText(this@MainActivity,"($position)",Toast.LENGTH_SHORT).show()
                    var i = Intent(this@MainActivity,DetailsActivity::class.java)
                    i.putExtra("type",it[position].type)
                    i.putExtra("price",it[position].price.toString())
                    i.putExtra("img_src",it[position].img_src)
                    startActivity(i)
                }

            } )
        })

    }
}