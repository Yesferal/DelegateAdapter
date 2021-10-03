 [![](https://jitpack.io/v/Yesferal/DelegateAdapter.svg)](https://jitpack.io/#Yesferal/DelegateAdapter)
 # Delegate Adapter
 This library contains a [DelegateAdapter](https://github.com/Yesferal/DelegateAdapter/blob/main/delegate/src/main/java/com/yesferal/hornsapp/delegate/DelegateAdapter.kt), 
 which is an adapter you can link with your RecyclerView in any Android project.
 You can define all your views as Delegates, and then insert them into this adapter.

 ## Getting Started
 In your project gradle, you should add the repository:
 ```kotlin
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
 ```
 and, in your app gradle, add the dependency:
 ```kotlin
 implementation 'com.github.Yesferal:DelegateAdapter:${version}'
 ```

 Now, you can instance a DelegateAdapter in your Activity or Fragment, as you normally do.

 ## How to: Initialize the DelegateAdapter
You can initialize it using its own Builder:
 ```kotlin
 val delegateAdapter = DelegateAdapter.Builder().build()
 ```

 ### Builder Methods
 The DelegateAdapter's builder has the following methods:
 - addItem ([Any kind of Delegate](https://github.com/Yesferal/DelegateAdapter/tree/main/delegate/src/main/java/com/yesferal/hornsapp/delegate/delegate)): If you have to insert just one Delegate into the list.
   - The default value of this list is an empty List.
 - addItems(List<[Any kind of Delegate](https://github.com/Yesferal/DelegateAdapter/tree/main/delegate/src/main/java/com/yesferal/hornsapp/delegate/delegate)>): If you have to insert a bunch of Delegates into the list.
   - The default value of this list is an empty List.
 - setListener (DelegateListener): If you have to handle some custom behaviour in your Activity/Fragment.
   - The default value is a default Listener.
   - To understand the listener parameter, and how to add custom Listeners,
 you can check the [Declare-a-Delegate-Listener](https://github.com/Yesferal/DelegateAdapter#Declare-a-Delegate-Listener) section.

 For instance, you can initialize the DelegateAdapter and assign it to your RecyclerView as simple as:
 ```kotlin
 override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    
    val delegateAdapter = DelegateAdapter().Builder()
        .addItem(object : Delegate {
            override fun onBindViewHolder(view: View, listener: DelegateListener) {}

            override val layout: Int
                get() = R.layout.item_title

        }).build()
    
    findViewById<RecyclerView>(R.id.recyclerView).let {
        it.layoutManager = LinearLayoutManager(it.context, LinearLayoutManager.VERTICAL, false)
        it.adapter = delegateAdapter
    }
 }
 ```

### What kind of Delegate exist?
Before you insert any delegate in the DelegateAdapter, 
you make sure that it implements either `InteractiveDelegate` or `NonInteractiveDelegate`.
   - [NonInteractiveDelegate](https://github.com/Yesferal/DelegateAdapter#NonInteractiveDelegate):
If the Delegate won't interact with any class
   - [InteractiveDelegate](https://github.com/Yesferal/DelegateAdapter#InteractiveDelegate):
If the Delegate will interact with your Activity/Fragment

Or, you can create an instance of RowDelegate in order to add a row of delegates:
   - [RowDelegate](https://github.com/Yesferal/DelegateAdapter#RowDelegate):
If you have to make a Horizontal list of delegates, in this case just use its builder in order to create it

## How to: Insert or Update delegates
First, your Delegate should implement either InteractiveDelegate or NonInteractiveDelegate, or be a RowDelegate.
After that you can include it in your DelegateAdapter:
```kotlin
data class TitleDelegate(
    val title: String
) : NonInteractiveDelegate { /** Will be explained later **/ }

val delegateAdapter = DelegateAdapter().Builder().build()
 
delegateAdapter.updateDelegates(
    listOf(
        TitleDelegate(title = "Section #1"),
        RowDelegate().Builder().build()
    )
)
```

## NonInteractiveDelegate
 For instance, imagine that you need a Delegate with just one attribute(`title: String`),
 then your class will look this way:
 ```kotlin
data class TitleDelegate(
    val title: String
) : NonInteractiveDelegate
 ```

### Override NonInteractiveDelegate

 As you could see, when you extend from `NonInteractiveDelegate` you have to override:
 - `onBindViewDelegate`: Here you will bind the view elements with the delegate attributes, as you normally do in a `ViewHolder`.
 - `layout`: This will be the layout's id you are using for this delegate.

After you implement them your class will look like:
```kotlin
 data class TitleDelegate(
    val title: String
) : NonInteractiveDelegate {

    override fun onBindViewDelegate(view: View) {
        view.findViewById<TextView>(R.id.title).text = title
    }

    override val layout: Int
        get() = R.layout.item_title
}
```

## InteractiveDelegate
When we use a RecyclerView we also have to implement an Adapter, 
and sometimes we need a Listener to communicate our Activity/Fragment that the Adapter had an interaction.

Here we can use `InteractiveDelegate`, 
but as you can see the `InteractiveDelegate` expect a `DelegateListener`.

```kotlin
class TitleDelegate(
    val title: String
) : InteractiveDelegate</** Expect a DelegateListener **/>
```

 ### Declare a DelegateListener
To declare a custom Delegate Listener you just need to create an interface (e.g. `CustomListener`)
that implement the DelegateListener.
```kotlin
interface CustomListener: DelegateListener {
    fun onClick(titleDelegate: TitleDelegate)
}
```

 This interface should contain all the interactions you need, like `onClick`.
 The only condition is to implement DelegateListener.

Now you can link this Listener with your Delegate, of course your Delegate should be an InteractiveDelegate.
```kotlin
class TitleDelegate(
    val title: String
) : InteractiveDelegate<CustomListener>
```

### Call the DelegateListener's methods

As you could see, when you extend from InteractiveDelegate you have to override:
- `onBindViewDelegate`: Here you will bind the view elements with the delegate attributes, as you normally do in a ViewHolder
    - Plus, here you will have an instance (`listener: CustomListener`) of your CustomListener
- `layout`: This will be the layout's id you are using for this delegate

After you implement them your class will look like:
```kotlin
 class TitleDelegate(
    val title: String
) : InteractiveDelegate<CustomListener> {
    
    override fun onBindViewDelegate(view: View, listener: CustomListener) {
        view.title.text = title
        view.setOnClickListener {
            listener.onClick(this)
        }
    }

    override val layout: Int
        get() = R.layout.item_title
}
```

### Implement the DelegateListener's methods
Due to we have to interact with the Activity/Fragment, 
then we have to override the listener and link it with our `DelegateAdapter` too.
As we already know, we can insert a `DelegateListener` in the `DelegateAdapter` using its builder.
In this example we will pass the Listener implementation as an object.
 ```kotlin
val delegateAdapter = DelegateAdapter().Builder()
                            .setListener(object: CustomListener {
                                override fun onClick(titleDelegate: TitleDelegate) {
                                    TODO("Not yet implemented")
                                }
                            })
                            .build()
 ```

## RowDelegate
If you want to add a Horizontal list of Delegates (Nested RecyclerView), you can use a RowDelegate.
In that case you just need to create a RowDelegate using its builder.

````kotlin
val rowDelegate = RowDelegate.Builder()
    .addItems(
        listOf(
            TitleDelegate(title = "Landscape (1/3)"),
            TitleDelegate(title = "Landscape (2/3)"),
            TitleDelegate(title = "Landscape (3/3)")
        )
    )
    .addBackground(R.color.white)
    .addElevation(16F)
    .build()

val delegateAdapter = DelegateAdapter.Builder().addItem(rowDelegate).build()
````

This will give you a Delegate instance that will work as a Horizontal Recycler View, 
so you will be able to scroll horizontally too. 
Now your delegate adapter will work in any direction.

### Builder methods
The RowDelegate's builder has four methods:
  - addItems (List<[Any kind of Delegate](https://github.com/Yesferal/DelegateAdapter/tree/main/delegate/src/main/java/com/yesferal/hornsapp/delegate/delegate)>):
If you have to insert a bunch of Delegates into the horizontal list
  - addHorizontalMargin (Int): If you have to add a start and end margins, like a Decorator but more simple
  - addBackground (Int): If you have to set a background color
  - addElevation (Float): If you have to set a view elevation

 ## Demo Projects
 In this Repository you have a Sample App that explain how to use this DelegateAdapter.

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
