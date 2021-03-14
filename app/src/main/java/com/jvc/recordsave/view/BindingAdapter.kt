package com.jvc.recordsave.view

import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("errorText")
fun setErrorMessage(view: TextInputLayout, errorMessage: MutableLiveData<String>) {
    errorMessage.value?.let {
        view.error = errorMessage.value
    }
}


@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, items: T) {

    if (items == null)
        return

    if (recyclerView.adapter is BindableAdapter<*>) {
        (recyclerView.adapter as BindableAdapter<T>).setData(items)
    }

}


interface BindableAdapter<T> {
    fun setData(data: T)
}