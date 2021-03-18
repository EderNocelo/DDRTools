/**
Copyright 2021 DDRTools

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
 * */
package com.mx.ddrtools.components.snackbar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.paris.extensions.style
import com.google.android.material.snackbar.ContentViewCallback
import com.mx.ddrtools.R
import com.mx.ddrtools.databinding.CustomSnackBarBinding

class DDRSnackBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ContentViewCallback {

    private var viewBinding: CustomSnackBarBinding = CustomSnackBarBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {
        View.inflate(context, R.layout.custom_snack_bar, this)
        clipToPadding = false
    }

    fun setBackground(@DrawableRes background: Int) = rootView.setBackgroundResource(background)

    fun setMessage(@StringRes message: Int) = viewBinding.apply { tvSBMessage.setText(message) }

    fun setMessageStyle(@StyleRes textAppearance: Int?) = viewBinding.apply {
        textAppearance?.let {
            tvSBMessage.style(it)
        }
    }

    fun setIconResource(@DrawableRes icon: Int?) = viewBinding.apply {
        icon?.let {
            ivSBIcon.setImageResource(icon)
        } ?: run {
            ivSBIcon.visibility = View.GONE
        }
    }

    fun setStatus(status: Boolean) = viewBinding.apply {
        ivStatusIcon.visibility = View.VISIBLE
        ivStatusIcon.setImageResource(if (status) R.drawable.ic_done_rounded else R.drawable.ic_cancel_rounded)
    }

    override fun animateContentIn(delay: Int, duration: Int) {}
    override fun animateContentOut(delay: Int, duration: Int) {}
}