package com.gig.hue.adapters

import android.view.View
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.BindingAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.textfield.TextInputLayout
import com.google.android.material.textview.MaterialTextView


@BindingAdapter("showProgress")
fun bindShowProgress(progressBar: ContentLoadingProgressBar, showProgress: Boolean) =
    if (showProgress) progressBar.show() else progressBar.hide()

@BindingAdapter("isEnabled")
fun bindIsEnabled(view: View, isEnabled: Boolean) {
    view.isEnabled = isEnabled
}

@BindingAdapter("displayError")
fun bindDisplayError(textInputLayout: TextInputLayout, errorMessage: Int?) {
    errorMessage?.let {
        textInputLayout.isErrorEnabled = true
        textInputLayout.error = textInputLayout.context.getString(errorMessage)
        } ?: run {
        textInputLayout.isErrorEnabled = false
        textInputLayout.error = null
    }
}

@BindingAdapter("onItemSelected")
fun bindOnItemSelected(bottomNavigationView: BottomNavigationView, listener: NavigationBarView.OnItemSelectedListener){
    bottomNavigationView.setOnItemSelectedListener(listener)
}

@BindingAdapter("visibleWhenNotNull")
fun bindVisibleWhenNotNull(view: View, data: Any?) {
    data?.let {
        view.visibility = View.VISIBLE
    } ?: run {
       view.visibility = View.GONE
    }
}