package com.yesferal.hornsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.yesferal.hornsapp.model.MainCardModel
import com.yesferal.hornsapp.model.SecondaryCardModel
import com.yesferal.hornsapp.multitype.MultiTypeAdapter
import com.yesferal.hornsapp.util.RecyclerViewVerticalDecorator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var multiTypeAdapter: MultiTypeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        multiTypeAdapter = MultiTypeAdapter(instanceAdapterListener())
        recyclerView.also {
            it.adapter = multiTypeAdapter
            it.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
            it.addItemDecoration(RecyclerViewVerticalDecorator())
        }

        multiTypeAdapter.setItems(ViewGenerator.models)
    }
}

private fun MainActivity.instanceAdapterListener() =
        object : MainCardModel.Listener,
        SecondaryCardModel.Listener {
            override fun onClick(mainCardModel: MainCardModel) {
                Toast.makeText(this@instanceAdapterListener, "You click on main object: $mainCardModel", Toast.LENGTH_SHORT)
                        .show()
            }

            override fun onClick(secondaryCardModel: SecondaryCardModel) {
                Toast.makeText(this@instanceAdapterListener, "You click on secondary object: $secondaryCardModel", Toast.LENGTH_SHORT)
                        .show()
            }
        }