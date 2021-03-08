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
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.ContentViewCallback
import com.mx.ddrtools.R

class DDRSnackBarView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), ContentViewCallback {

    private var ivIcon: ImageView
    private var tvMessage: TextView

    init {
        View.inflate(context, R.layout.custom_snack_bar, this)
        clipToPadding = false
        ivIcon = findViewById(R.id.ivSBIcon)
        tvMessage = findViewById(R.id.tvSBMessage)
    }

    fun setBackground(@DrawableRes background: Int) {
        rootView.setBackgroundResource(background)
    }

    fun setMessage(@StringRes message:Int, @StyleRes textAppearance:Int) {
        tvMessage.setText(message)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            tvMessage.setTextAppearance(textAppearance)
        }else{
            tvMessage.setTextAppearance(context,textAppearance)
        }
    }

    fun setIconResource(@DrawableRes icon: Int?) {
        icon?.let {
            ivIcon.setImageResource(icon)
        } ?: run{
            ivIcon.visibility = View.GONE
        }
    }

    override fun animateContentIn(delay: Int, duration: Int) {}
    override fun animateContentOut(delay: Int, duration: Int) {}
}