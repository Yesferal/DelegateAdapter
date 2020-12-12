package com.yesferal.hornsapp

import com.yesferal.hornsapp.model.MainCardModel
import com.yesferal.hornsapp.model.SecondaryCardModel
import com.yesferal.hornsapp.model.TitleModel

object ViewGenerator {
    val models = listOf(
            MainCardModel(title = "Main Landscape", image = R.drawable.image),
            TitleModel(title = "Section #1"),
            SecondaryCardModel(title = "Landscape #1.1", description = "Section #1 & Image #1", image = R.drawable.image1),
            SecondaryCardModel(title = "Landscape #1.2", description = "Section #1 & Image #2", image = R.drawable.image2),
            SecondaryCardModel(title = "Landscape #1.3", description = "Section #1 & Image #3", image = R.drawable.image3),
            TitleModel(title = "Section #2"),
            SecondaryCardModel(title = "Landscape #2.1", description = "Section #2 & Image #1", image = R.drawable.image2),
            SecondaryCardModel(title = "Landscape #2.2", description = "Section #2 & Image #2", image = R.drawable.image4),
            TitleModel(title = "Section #3"),
            SecondaryCardModel(title = "Landscape #3.1", description = "Section #2 & Image #1", image = R.drawable.image1),
            SecondaryCardModel(title = "Landscape #3.2", description = "Section #2 & Image #2", image = R.drawable.image3),
            SecondaryCardModel(title = "Landscape #3.3", description = "Section #2 & Image #3", image = R.drawable.image4)

    )
}