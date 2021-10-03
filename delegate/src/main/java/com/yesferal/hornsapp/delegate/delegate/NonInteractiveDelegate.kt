package com.yesferal.hornsapp.delegate.delegate

import android.view.View
import com.yesferal.hornsapp.delegate.abstraction.Delegate
import com.yesferal.hornsapp.delegate.abstraction.DelegateListener

interface NonInteractiveDelegate: Delegate {

    fun onBindViewDelegate(view: View)

    override fun onBindViewHolder(view: View, listener: DelegateListener) {
        onBindViewDelegate(view)
    }
}
