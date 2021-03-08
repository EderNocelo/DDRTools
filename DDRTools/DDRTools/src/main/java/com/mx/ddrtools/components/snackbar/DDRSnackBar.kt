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

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.mx.ddrtools.R
import com.mx.ddrtools.utils.findSuitableParent

class DDRSnackBar(
    parent: ViewGroup,
    content: DDRSnackBarView
) : BaseTransientBottomBar<DDRSnackBar>(parent, content,content) {

    init {
        getView().setBackgroundColor(
            ContextCompat.getColor(
                view.context,
                android.R.color.transparent
            )
        )
        getView().setPadding(0, 0, 0, 0)
    }

    companion object {

        fun make(
            view: View,
            @StringRes message: Int,
            @DrawableRes icon: Int? = null,
            @DrawableRes background: Int = R.drawable.custom_snack_bar_background,
            @StyleRes messageStyle: Int = R.style.DDRSnackBarTextStyle,
            onClick: (DDRSnackBar) -> Unit
        ): DDRSnackBar {

            // First we find a suitable parent for our custom view
            val parent = view.findSuitableParent() ?: throw IllegalArgumentException(
                "No suitable parent found from the given view. Please provide a valid view."
            )

            // We inflate our custom view
            val customView = LayoutInflater.from(view.context).inflate(
                R.layout.custom_snack_bar_container,
                parent,
                false
            ) as DDRSnackBarView

            customView.setBackground(background)
            customView.setMessage(message, messageStyle)
            customView.setIconResource(icon)

            val instance = DDRSnackBar(
                parent,
                customView
            )
            // Lambda for onClickListener
            customView.setOnClickListener {
                onClick(instance)
            }

            instance.duration = LENGTH_SHORT

            // We create and return common SnackBar
            return instance
        }
    }
}