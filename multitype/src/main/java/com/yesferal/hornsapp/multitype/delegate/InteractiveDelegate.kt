package com.yesferal.hornsapp.multitype.delegate

import android.view.View
import com.yesferal.hornsapp.multitype.abstraction.DelegateListener
import com.yesferal.hornsapp.multitype.abstraction.Delegate

typealias NonInteractiveDelegate = InteractiveDelegate<DelegateListener>

interface InteractiveDelegate<LISTENER : DelegateListener> : Delegate {

    fun onBindViewDelegate(view: View, listener: LISTENER)

    override fun onBindViewHolder(view: View, listener: DelegateListener) {
        @Suppress("UNCHECKED_CAST")
        onBindViewDelegate(view, listener as LISTENER)
    }
}
