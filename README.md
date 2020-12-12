 # HornsApp MultiType Adapter
 This library contains a multi-type adapter class, which you can use very simple on any Android project.
 Now, you can define all your custom views as a `ViewHolder`, binding to a model
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
 you just need to read the [How to: Define a model with a Custom Listener](https://github.com/Yesferal/MultiTypeAdapter#how-to-define-a-model-with-a-custom-listener).
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

 Finally, when you need to add your models you should add this line:
 ```kotlin
 multiTypeAdapter.setModels(listOf(/* Your models will be here */))
 ```

 ### How to: Define a simple model
 If you want to add any object in the `MultiTypeAdapter`'s list, you need that these objects implement the `ViewHolderBinding` interface.
 Because `MultiTypeAdapter` only recognise objects with this implementation.
 For example, imagine that you need a model with one attribute, which could be `text: String`,
 so your class should look like this:
 ```kotlin
 data class TitleModel(
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
     ) : BaseViewHolder<TitleModel>(itemView) {
         override fun bind(model: TitleModel) {
             itemView.title.text = model.title
         }
     }
 }
 ```

 As you could see at the beginning, when you implement `ViewHolderBinding` you need to:
 - Override the `onCreateViewHolder` function
   - In this function you will define the `ViewHolder` you will bind to your model.
     - In this case we create another class call `TitleViewHolder`.
     - The `TitleViewHolder` should extend from `BaseViewHolder`.
 - Override the `layout` attribute
   - In the `layout` variable you must assign the layout's id (This is an `Int`).

 And that's it, now you can include `TitleModel` as an element of `MultiTypeAdapter`'s list:
  ```kotlin
  multiTypeAdapter.setModels(listOf(TitleModel(title = "Section #1")))
  ```

 ### How to: Define a model with a Custom Listener
 When we use a `RecyclerView` we always implement a `ViewHolder`
 and frequently we need a Custom Listener to communicate the `ViewHolder` with the Activities or Fragments.

 #### Define a Custom Listener
 So, to create a Custom Listener you just need to create an interface (for example `CustomListener`)
 and implement the `ViewHolderBinding.Listener`.
 ```kotlin
 interface CustomListener: ViewHolderBinding.Listener {
     fun onClick(mainCardModel: MainCardModel)
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
 ) : BaseViewHolder<MainCardModel>(itemView) {
     override fun bind(model: MainCardModel) {
          itemView.title.text = model.title
          itemView.image.setImageResource(model.image)
          itemView.setOnClickListener {
              listener.onClick(model)
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

 #### The entire model implementation
 For example, if we have a class `MainCardModel`, as we could see at the beginning, we need to:
  * Override `onCreateViewHolder` function
  * Define a custom `ViewHolder` class
    * In this case we will call it: `MainViewHolder`
  * Override `layout` attribute.

 But now, we also need to create a `CustomListener` interface. This would be the entire class:
 ```kotlin
 class MainCardModel(
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
         fun onClick(mainCardModel: MainCardModel)
     }

     class MainViewHolder(
            itemView: View,
            private val listener: CustomListener
     ) : BaseViewHolder<MainCardModel>(itemView) {
         override fun bind(model: MainCardModel) {
             itemView.title.text = model.title
             itemView.image.setImageResource(model.image)
             itemView.setOnClickListener {
                 listener.onClick(model)
             }
         }
    }
 }
 ```

 #### Override the functions of our Custom Listener
 Finally, we have a `CustomListener`, however we have to override the `onClick(mainCardModel: MainCardModel)` function.
 We should go to us Activity or Fragment.
 As we already know, we can pass a parameter in the constructor of our `MultiTypeAdapter`
 that implement `ViewHolderBinding.Listener`.
 In this example we will pass an `object : MainCardModel.CustomListener`.
 But you can fill the body of `onClick(mainCardModel: MainCardModel)` function as you need it.
 ```kotlin
 multiTypeAdapter = MultiTypeAdapter(listener = object : MainCardModel.CustomListener {
     override fun onClick(mainCardModel: MainCardModel) {
         // Here you can add your code
     }
 })
 ```

 ### Add your models
 And that's it, again, you can include `MainCardModel` as an element of `MultiTypeAdapter`'s list:
  ```kotlin
  multiTypeAdapter.setModels(
      listOf(
          // Model without Custom Listener
          TitleModel(title = "Section #1"),
          // Your new model with a Custom Listener
          MainCardModel(title = "Main Landscape", image = R.drawable.image)
      )
  )
  ```

 ## Demo Projects
 In this Repository you have an Android Demo that explain how to use this `MultiTypeAdapter`.

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
