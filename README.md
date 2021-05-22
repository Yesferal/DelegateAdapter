 # MultiType Adapter
 This library contains a multi-type adapter class, which you can use very simple on any Android project.
 Now, you can define all your custom views as a `ViewHolder`, binding to an item
 and adding to the adapter as a list of `ViewHolderBinding`.

 ## Getting Started
 In your app gradle, you should add the dependency:
 ```kotlin
 implementation 'com.yesferal.hornsapp:multitype:1.0.1'
 ```

 Then, you could instance a `MultiTypeAdapter` in your activity or fragment, as you normally use to do it.

 ### How to: Initialize MultiTypeAdapter
 First, you have to declare it as a variable, I would recommend that you do it as a lateinit variable:
 ```kotlin
 private lateinit var multiTypeAdapter: MultiTypeAdapter
 ```

 and finally you could initialize it wherever you want, just adding the next line:
 ```kotlin
 multiTypeAdapter = MultiTypeAdapter()
 ```

 #### MultiType's Constructor
 The `MultiTypeAdapter`'s constructor accept two optional parameters:
 - `listener: ViewHolderBinding.Listener`: The first one is a listener.
   - The default value is `object : ViewHolderBinding.Listener {}`.
   - To understand the listener parameter or how to add Custom Listeners
 you just need to read the [How to: Define an item with a Custom Listener](https://github.com/Yesferal/MultiTypeAdapter#how-to-define-a-item-with-a-custom-listener).
 - `list: MutableList<ViewHolderBinding>`: The second parameter is a list of `ViewHolderBinding`.
   - The default value is `mutableListOf()`.

 For example, you could instance the `MultiTypeAdapter` inside the `onCreate` function,
 and assign it to your `RecyclerView`:
 ```kotlin
 override fun onCreate(savedInstanceState: Bundle?) {
     super.onCreate(savedInstanceState)
     setContentView(R.layout.activity_main)

     multiTypeAdapter = MultiTypeAdapter()

     recyclerView.also {
         it.adapter = multiTypeAdapter
         it.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
     }
 }
 ```

 Finally, when you need to add your items you should add this line:
 ```kotlin
 multiTypeAdapter.setItems(listOf(/* Your items will be here */))
 ```

 ### How to: Define a simple item
 If you want to add any object in the `MultiTypeAdapter`'s list, you need that these objects implement the `ViewHolderBinding` interface.
 Because `MultiTypeAdapter` only recognise objects with this implementation.
 For example, imagine that you need an item with one attribute, which could be `text: String`,
 so your class should look like this:
 ```kotlin
 data class TitleItem(
     val title: String
 ) : ViewHolderBinding {
     override fun onCreateViewHolder(
         itemView: View,
         listener: ViewHolderBinding.Listener
     ) = TitleViewHolder(itemView)

     override val layout: Int
         get() = R.layout.item_title

     // Your Custom ViewHolder
     class TitleViewHolder(
             itemView: View
     ) : BaseViewHolder<TitleItem>(itemView) {
         override fun bind(item: TitleItem) {
             itemView.title.text = item.title
         }
     }
 }
 ```

 As you could see at the beginning, when you implement `ViewHolderBinding` you need to:
 - Override the `onCreateViewHolder` function
   - In this function you will define the `ViewHolder` you will bind to your item.
     - In this case we create another class call `TitleViewHolder`.
     - The `TitleViewHolder` should extend from `BaseViewHolder`.
 - Override the `layout` attribute
   - In the `layout` variable you must assign the layout's id (This is an `Int`).

 And that's it, now you can include `TitleItem` as an element of `MultiTypeAdapter`'s list:
  ```kotlin
  multiTypeAdapter.setItems(listOf(TitleItem(title = "Section #1")))
  ```

 ### How to: Define a item with a Custom Listener
 When we use a `RecyclerView` we always implement a `ViewHolder`
 and frequently we need a Custom Listener to communicate the `ViewHolder` with the Activities or Fragments.

 #### Define a Custom Listener
 So, to create a Custom Listener you just need to create an interface (for example `CustomListener`)
 and implement the `ViewHolderBinding.Listener`.
 ```kotlin
 interface CustomListener: ViewHolderBinding.Listener {
     fun onClick(mainCard: MainCard)
 }
 ```

 This interface can contains any function that you could need or imagine.
 The only condition is that the `CustomInterface` has to implement `ViewHolderBinding.Listener`.

 #### Call the functions defined in your Custom Listener
 Then, if you want to use any functions, defined previously in `CustomInterface`,
 you just need to insert the listener in your `MainViewHolder`'s constructor as another parameter, just like itemView.
 ```kotlin
 class MainViewHolder(
     itemView: View,
     // This is the new parameter,
     // now you can use your Custom Listener
     private val listener: CustomListener
 ) : BaseViewHolder<MainCard>(itemView) {
     override fun bind(item: MainCard) {
          itemView.title.text = item.title
          itemView.image.setImageResource(item.image)
          itemView.setOnClickListener {
              listener.onClick(item)
          }
      }
  }
 ```

 #### Intercept the Listener instance
 Is important to say that, when we override the function `onCreateViewHolder`
 we also need to pass a second parameter to our custom `ViewHolder`.
 Of course, it is the generic Listener parsed as a `CustomListener`:
 ```kotlin
  override fun onCreateViewHolder(
      itemView: View,
      listener: ViewHolderBinding.Listener // Generic Listener
  // We need to add a new parameter
  // and parse the generic Listener as a Custom Listener
  ) = MainViewHolder(itemView, listener as CustomListener)
 ```

 #### The entire item implementation
 For example, if we have a class `MainCard`, as we could see at the beginning, we need to:
  * Override `onCreateViewHolder` function
  * Define a custom `ViewHolder` class
    * In this case we will call it: `MainViewHolder`
  * Override `layout` attribute.

 But now, we also need to create a `CustomListener` interface. This would be the entire class:
 ```kotlin
 class MainCard(
     val title: String,
     @DrawableRes val image: Int
 ) : ViewHolderBinding {
     override fun onCreateViewHolder(
         itemView: View,
         listener: ViewHolderBinding.Listener
     ) = MainViewHolder(itemView, listener as CustomListener)

     override val layout: Int
         get() = R.layout.item_main_card

     // New Custom Listener
     interface CustomListener: ViewHolderBinding.Listener {
         fun onClick(mainCard: MainCard)
     }

     class MainViewHolder(
            itemView: View,
            private val listener: CustomListener
     ) : BaseViewHolder<MainCard>(itemView) {
         override fun bind(item: MainCard) {
             itemView.title.text = item.title
             itemView.image.setImageResource(item.image)
             itemView.setOnClickListener {
                 listener.onClick(item)
             }
         }
    }
 }
 ```

 #### Override the functions of our Custom Listener
 Finally, we have a `CustomListener`, however we have to override the `onClick(mainCard: MainCard)` function.
 We should go to us Activity or Fragment.
 As we already know, we can pass a parameter in the constructor of our `MultiTypeAdapter`
 that implement `ViewHolderBinding.Listener`.
 In this example we will pass an `object : MainCard.CustomListener`.
 But you can fill the body of `onClick(mainCard: MainCard)` function as you need it.
 ```kotlin
 multiTypeAdapter = MultiTypeAdapter(listener = object : MainCard.CustomListener {
     override fun onClick(mainCard: MainCard) {
         // Here you can add your code
     }
 })
 ```

 ### Add your items
 And that's it, again, you can include `MainCard` as an element of `MultiTypeAdapter`'s list:
  ```kotlin
  multiTypeAdapter.setItems(
      listOf(
          // Item without Custom Listener
          TitleItem(title = "Section #1"),
          // Your new item with a Custom Listener
          MainCard(title = "Main Landscape", image = R.drawable.image)
      )
  )
  ```

 ## Demo Projects
 In this Repository you have a Sample Android App that explain how to use this `MultiTypeAdapter`.

 ## License
 ```
 Copyright 2020 HornsApp Contributors

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

      https://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 ```
