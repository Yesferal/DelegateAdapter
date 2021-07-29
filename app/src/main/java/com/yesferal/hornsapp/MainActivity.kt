package com.yesferal.hornsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yesferal.hornsapp.model.MainCard
import com.yesferal.hornsapp.model.SecondaryCard
import com.yesferal.hornsapp.multitype.BaseItem
import com.yesferal.hornsapp.multitype.MultiTypeAdapter
import com.yesferal.hornsapp.util.RecyclerViewVerticalDecorator

class MainActivity : AppCompatActivity(), MainCard.Listener, SecondaryCard.Listener {

    lateinit var multiTypeAdapter: MultiTypeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = ViewGenerator.items

        multiTypeAdapter = MultiTypeAdapter.Builder()
                .addItem(MainCard(title = "Main Landscape", image = R.drawable.image))
                .addItems(items)
                .setListener(this)
                .build()

        findViewById<RecyclerView>(R.id.recyclerView).also {
            it.adapter = multiTypeAdapter
            it.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
            it.addItemDecoration(RecyclerViewVerticalDecorator())
        }
    }

    /**
     * If you need to update the items
     * or you are waiting for a service response
     */
    fun updateView(items: List<BaseItem<BaseItem.Listener>>) {
        multiTypeAdapter.updateItems(items)
    }

    override fun onClick(mainCard: MainCard) {
        Toast.makeText(this, "You click on main object: $mainCard", Toast.LENGTH_SHORT)
                .show()
    }

    override fun onClick(secondaryCard: SecondaryCard) {
        Toast.makeText(this, "You click on secondary object: $secondaryCard", Toast.LENGTH_SHORT)
                .show()
    }
}
