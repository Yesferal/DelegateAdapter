package com.yesferal.hornsapp

import com.yesferal.hornsapp.model.CarouselDelegate
import com.yesferal.hornsapp.model.SecondaryDelegate
import com.yesferal.hornsapp.model.TitleDelegate
import com.yesferal.hornsapp.multitype.delegate.RowDelegate

object ViewGenerator {
    val items = listOf(
        TitleDelegate(title = "Section #1"),
        RowDelegate.Builder()
            .addItems(listOf(
                CarouselDelegate(
                    title = "Landscape #1.1",
                    image = R.drawable.image1
                ),
                CarouselDelegate(
                    title = "Landscape #1.2",
                    image = R.drawable.image2
                ),
                CarouselDelegate(
                    title = "Landscape #1.3",
                    image = R.drawable.image3
                ),
                CarouselDelegate(
                    title = "Landscape #1.4",
                    image = R.drawable.image4
                ),
                CarouselDelegate(
                    title = "Landscape #1.5",
                    image = R.drawable.image2
                ),
                CarouselDelegate(
                    title = "Landscape #1.6",
                    image = R.drawable.image4
                ),
                CarouselDelegate(
                    title = "Landscape #1.7",
                    image = R.drawable.image1
                )
            ))
            .addHorizontalMargin(8)
            .build(),
        TitleDelegate(title = "Section #2"),
        RowDelegate.Builder()
            .addItems(listOf(
                CarouselDelegate(
                    title = "Landscape #2.1",
                    image = R.drawable.image2
                ),
                CarouselDelegate(
                    title = "Landscape #2.2",
                    image = R.drawable.image5
                ),
                CarouselDelegate(
                    title = "Landscape #2.3",
                    image = R.drawable.image3
                ),
                CarouselDelegate(
                    title = "Landscape #2.4",
                    image = R.drawable.image4
                ),
                CarouselDelegate(
                    title = "Landscape #2.5",
                    image = R.drawable.image1
                )
            ))
            .addHorizontalMargin(8)
            .build(),
        TitleDelegate(title = "Section #3"),
        RowDelegate.Builder()
            .addItems(listOf(
                CarouselDelegate(
                    title = "Landscape #3.1",
                    image = R.drawable.image1
                ),
                CarouselDelegate(
                    title = "Landscape #3.2",
                    image = R.drawable.image2
                ),
                CarouselDelegate(
                    title = "Landscape #3.3",
                    image = R.drawable.image3
                ),
                CarouselDelegate(
                    title = "Landscape #3.4",
                    image = R.drawable.image5
                ),
                CarouselDelegate(
                    title = "Landscape #3.5",
                    image = R.drawable.image2
                )
            ))
            .addHorizontalMargin(8)
            .build(),
        TitleDelegate(title = "Section #4"),
        SecondaryDelegate(
            title = "Landscape #4.1",
            description = "Section #4 & Image #1",
            image = R.drawable.image4
        ),
        SecondaryDelegate(
            title = "Landscape #4.2",
            description = "Section #4 & Image #2",
            image = R.drawable.image3
        ),
        SecondaryDelegate(
            title = "Landscape #4.3",
            description = "Section #4 & Image #3",
            image = R.drawable.image2
        ),
        SecondaryDelegate(
            title = "Landscape #4.4",
            description = "Section #4 & Image #4",
            image = R.drawable.image1
        ),
        SecondaryDelegate(
            title = "Landscape #4.5",
            description = "Section #4 & Image #5",
            image = R.drawable.image3
        ),
        SecondaryDelegate(
            title = "Landscape #4.6",
            description = "Section #4 & Image #6",
            image = R.drawable.image4
        ),
        SecondaryDelegate(
            title = "Landscape #4.7",
            description = "Section #4 & Image #7",
            image = R.drawable.image5
        ),
        SecondaryDelegate(
            title = "Landscape #4.8",
            description = "Section #4 & Image #8",
            image = R.drawable.image3
        )
    )
}
