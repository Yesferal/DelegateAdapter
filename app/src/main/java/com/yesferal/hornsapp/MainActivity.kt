package com.yesferal.hornsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.yesferal.hornsapp.model.CarouselCard
import com.yesferal.hornsapp.model.MainCard
import com.yesferal.hornsapp.model.SecondaryCard
import com.yesferal.hornsapp.multitype.delegate.RowDelegate
import com.yesferal.hornsapp.multitype.delegate.MultiTypeContainer
import com.yesferal.hornsapp.multitype.abstraction.Delegate
import com.yesferal.hornsapp.util.RecyclerViewVerticalDecorator

class MainActivity : AppCompatActivity(), MainCard.Listener, SecondaryCard.Listener,
    CarouselCard.Listener {

    private lateinit var mtContainer: MultiTypeContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = ViewGenerator.items

        mtContainer = MultiTypeContainer.Builder()
            .addItem(
                RowDelegate.Builder()
                .addItems(listOf(
                    MainCard(title = "Main Landscape (1/3)", image = R.drawable.image5),
                    MainCard(title = "Main Landscape (2/3)", image = R.drawable.image1),
                    MainCard(title = "Main Landscape (3/3)", image = R.drawable.image2),
                ))
                .build()
            )
            .addItems(items)
            .setListener(this)
            .build()

        findViewById<RecyclerView>(R.id.recyclerView).let {
            it.addItemDecoration(RecyclerViewVerticalDecorator())
            mtContainer.attachRecycler(it)
        }
    }

    /**
     * If you need to update the items
     * or you are waiting for a service response
     */
    fun updateView(items: List<Delegate>) {
        mtContainer.updateItems(items)
    }

    override fun onClick(mainCard: MainCard) {
        Toast.makeText(this, "You click on main object: ${mainCard.title}", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onClick(secondaryCard: SecondaryCard) {
        Toast.makeText(this, "You click on secondary object: ${secondaryCard.title}", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onClick(carouselCard: CarouselCard) {
        Toast.makeText(this, "You click on secondary object: ${carouselCard.title}", Toast.LENGTH_SHORT)
            .show()
    }
}
