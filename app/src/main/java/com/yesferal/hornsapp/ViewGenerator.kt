package com.yesferal.hornsapp

import com.yesferal.hornsapp.model.SecondaryCard
import com.yesferal.hornsapp.model.TitleItem

object ViewGenerator {
    val items = listOf(
            TitleItem(title = "Section #1"),
            SecondaryCard(title = "Landscape #1.1", description = "Section #1 & Image #1", image = R.drawable.image1),
            SecondaryCard(title = "Landscape #1.2", description = "Section #1 & Image #2", image = R.drawable.image2),
            SecondaryCard(title = "Landscape #1.3", description = "Section #1 & Image #3", image = R.drawable.image3),
            TitleItem(title = "Section #2"),
            SecondaryCard(title = "Landscape #2.1", description = "Section #2 & Image #1", image = R.drawable.image2),
            SecondaryCard(title = "Landscape #2.2", description = "Section #2 & Image #2", image = R.drawable.image3),
            TitleItem(title = "Section #3"),
            SecondaryCard(title = "Landscape #3.1", description = "Section #3 & Image #1", image = R.drawable.image1),
            TitleItem(title = "Section #4"),
            SecondaryCard(title = "Landscape #4.1", description = "Section #4 & Image #1", image = R.drawable.image4),
            SecondaryCard(title = "Landscape #4.2", description = "Section #4 & Image #2", image = R.drawable.image3),
            SecondaryCard(title = "Landscape #4.3", description = "Section #4 & Image #3", image = R.drawable.image2),
            SecondaryCard(title = "Landscape #4.4", description = "Section #4 & Image #4", image = R.drawable.image1)
    )
}
