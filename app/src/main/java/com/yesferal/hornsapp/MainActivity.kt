package com.yesferal.hornsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.yesferal.hornsapp.model.MainCard
import com.yesferal.hornsapp.model.SecondaryCard
import com.yesferal.hornsapp.multitype.MultiTypeAdapter
import com.yesferal.hornsapp.util.RecyclerViewVerticalDecorator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val multiTypeAdapter = MultiTypeAdapter(listener = object :
                MainCard.Listener,
                SecondaryCard.Listener {
            override fun onClick(mainCard: MainCard) {
                Toast.makeText(this@MainActivity, "You click on main object: $mainCard", Toast.LENGTH_SHORT)
                        .show()
            }

            override fun onClick(secondaryCard: SecondaryCard) {
                Toast.makeText(this@MainActivity, "You click on secondary object: $secondaryCard", Toast.LENGTH_SHORT)
                        .show()
            }
        })

        recyclerView.also {
            it.adapter = multiTypeAdapter
            it.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
            it.addItemDecoration(RecyclerViewVerticalDecorator())
        }

        multiTypeAdapter.setItems(ViewGenerator.items)
    }
}
