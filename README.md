 [![](https://jitpack.io/v/Yesferal/DelegateAdapter.svg)](https://jitpack.io/#Yesferal/DelegateAdapter)
 # MultiType Adapter
 This library contains a delegate adapter class, which you can use in any Android project.
 You can define all your custom views as a `ViewDelegate`,
 and then insert them into this adapter.

 ## Getting Started
 In your app gradle, you should add the repository:
 ```kotlin
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
 ```
 and the dependency:
 ```kotlin
 implementation 'com.github.Yesferal:MultiTypeAdapter:1.4.0'
 ```

 Now, you could instance a `MultiTypeAdapter` in your Activity or Fragment, as you normally did.

 ## How to: Initialize MultiTypeAdapter
 First, you have to initialize it using its own Builder:
 ```kotlin
 private val multiTypeAdapter = MultiTypeAdapter.Builder().build()
 ```

 ### MultiType's Builder
 The `MultiTypeAdapter`'s builder receives three optional methods:
 - addItem(): If you have to insert just one BaseItem into the list.
   - The default value of this list is an empty `mutableListOf()`.
 - addItems(): If you have to insert a bunch of BaseItems into the list.
   - The default value of this list is an empty `mutableListOf()`.
 - setListener(): If you have to handle some behaviour in the Activity or Fragment.
   - The default value is an empty `BaseItem.Listener.
   - To understand the listener parameter, and how to add custom Listeners,
 you just need to read the [How to: Define an item with a Listener](https://github.com/Yesferal/MultiTypeAdapter#how-to-define-a-item-with-a-listener-or-callback) section.

 For instance, you could initialize the `MultiTypeAdapter` and assign it to your `RecyclerView` as simple as:
 ```kotlin
 override fun onCreate(savedInstanceState: Bundle?) {
     super.onCreate(savedInstanceState)
     setContentView(R.layout.activity_main)

     val multiTypeAdapter = MultiTypeAdapter().Builder()
                                .addItems(listOf(TitleItem("Title #1"), SectionItem("Section #1"), SectionItem("Section #2"))
                                .build()

     recyclerView.also {
         it.adapter = multiTypeAdapter
         it.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
     }
 }
 ```

 Finally, if you need to update your items later, then you could use this method:
 ```kotlin
 multiTypeAdapter.updateItems(listOf(/* Your items will be here */))
 ```

 ## How to: Define a simple item
 Before you insert any item in the `MultiTypeAdapter`'s list, you need that this object extends from `BaseItem`.
 For instance, imagine that you need an item with one attribute, which could be `title: String`,
 then your class will look this way:
 ```kotlin
 data class TitleItem(
     val title: String
 ) : BaseItem<BaseItem.Listener>() {

     override fun bind(view: View, listener: Listener) {
         view.title.text = title
     }

     override val layout: Int
         get() = R.layout.item_title
 }
 ```

 As you could see above, when you extend from `BaseItem` you have to:
 - Override the `bind` method
   - In this method you will bind the view and your item or model, as you normally do in a `ViewHolder`.
 - Override the `layout` attribute
   - The `layout` attribute must be the layout's id you are using for this item.

 And that's it, now you can include `TitleItem` in `MultiTypeAdapter`'s list:
  ```kotlin
  multiTypeAdapter.updateItems(listOf(TitleItem(title = "Section #1")))
  ```

 ## How to: Define an item with a Listener
 When we use a `RecyclerView` we always have to implement a `ViewHolder`,
 and sometimes we need a Listener to communicate the `ViewHolder` and the Activity or Fragment.

 ### Declare a Custom Listener
 So, to add a custom Listener you just need to create an interface (e.g. `CustomListener`)
 and implement the `BaseItem.Listener`.
 ```kotlin
 interface CustomListener: BaseItem.Listener {
     fun onClick(titleItem: TitleItem)
 }
 ```

 This interface could contains any method you need.
 The only condition is it has to implement `BaseItem.Listener`.

 ### Call the Custom Listener's methods
 Then, if you want to use any method defined previously in your `CustomInterface`
 you just need to replace the generic interface inside `BaseItem</*Here*/>`.
 And then update the bind method.
 ```kotlin
class TitleItem(
    val title: String
) : BaseItem<TitleItem.CustomListener>() {

    override fun bind(view: View, listener: CustomListener) {
        view.title.text = title
        view.setOnClickListener {
            listener.onClick(this)
        }
    }

    interface CustomListener: BaseItem.Listener {
         fun onClick(titleItem: TitleItem)
    }
}
```

 ### Set the Custom Listener into the MultiTypeAdapter
 We should return to our Activity or Fragment.
 As we already know, we can add a `BaseItem.Listener` in `MultiTypeAdapter` using its builder.
 In this example we will pass `this`, because Activity will implement our `TitleItem.CustomListener`.
 ```kotlin
 val multiTypeAdapter = MultiTypeAdapter.Builder()
                            .addItems(listOf(TitleItem("Title #1")))
                            .setListener(this)
                            .build()
 ```

### Override the Custom Listener's methods
As we know, if we use `this` as listener we should make that Activity extend from `TitleItem.CustomListener`.
After that we have to override the methods,
so now you can fill the body of `onClick(titleItem: TitleItem)` method as you need it.
 ```kotlin
 class MainActivity : AppCompatActivity(), TitleItem.CustomListener {
     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)

         val multiTypeAdapter = MultiTypeAdapter.Builder()
                 .addItems(listOf(TitleItem("Title #1")))
                 .setListener(this)
                 .build()

         recyclerView.also {
             it.adapter = multiTypeAdapter
             it.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
         }
     }

     override fun onClick(titleItem: TitleItem) {
         Toast.makeText(this, "You click on title item: $titleItem", Toast.LENGTH_SHORT)
                 .show()
     }
 }
 ```

 ## Demo Projects
 In this Repository you have a Sample App that explain how to use this `MultiTypeAdapter`.

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
