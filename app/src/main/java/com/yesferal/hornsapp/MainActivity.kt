package com.yesferal.hornsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.yesferal.hornsapp.model.CarouselDelegate
import com.yesferal.hornsapp.model.MainDelegate
import com.yesferal.hornsapp.model.SecondaryDelegate
import com.yesferal.hornsapp.multitype.delegate.RowDelegate
import com.yesferal.hornsapp.multitype.delegate.MultiTypeContainer
import com.yesferal.hornsapp.multitype.abstraction.Delegate
import com.yesferal.hornsapp.util.RecyclerViewVerticalDecorator

class MainActivity : AppCompatActivity(), MainDelegate.Listener, SecondaryDelegate.Listener,
    CarouselDelegate.Listener {

    private lateinit var mtContainer: MultiTypeContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val items = ViewGenerator.items

        mtContainer = MultiTypeContainer.Builder()
            .addItem(
                RowDelegate.Builder()
                .addItems(listOf(
                    MainDelegate(title = "Main Landscape (1/3)", image = R.drawable.image5),
                    MainDelegate(title = "Main Landscape (2/3)", image = R.drawable.image1),
                    MainDelegate(title = "Main Landscape (3/3)", image = R.drawable.image2),
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

    override fun onClick(mainDelegate: MainDelegate) {
        Toast.makeText(this, "You click on main object: ${mainDelegate.title}", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onClick(secondaryDelegate: SecondaryDelegate) {
        Toast.makeText(this, "You click on secondary object: ${secondaryDelegate.title}", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onClick(carouselDelegate: CarouselDelegate) {
        Toast.makeText(this, "You click on secondary object: ${carouselDelegate.title}", Toast.LENGTH_SHORT)
            .show()
    }
}
