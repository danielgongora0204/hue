package com.gig.hue.adapters

import android.view.View
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("showProgress")
fun bindShowProgress(progressBar: ContentLoadingProgressBar, showProgress: Boolean) =
    if (showProgress) progressBar.show() else progressBar.hide()

@BindingAdapter("isEnabled")
fun bindIsEnabled(view: View, isEnabled: Boolean) {
    view.isEnabled = isEnabled
}

@BindingAdapter("displayError")
fun bindDisplayError(textInputLayout: TextInputLayout, errorMessage: String?) {
    textInputLayout.isErrorEnabled = !errorMessage.isNullOrBlank()
    textInputLayout.error = errorMessage
}
