package com.mx.ddrtools.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.StyleRes
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.CompoundButtonCompat
import com.airbnb.paris.extensions.*
import org.jetbrains.annotations.NotNull


class DDRView {
    companion object {
        /**
         * Set text appearance of TextView
         * @param view current TextView
         * @param style current style
         */
        fun setTextViewStyle(@NotNull view: TextView, @StyleRes style: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                view.setTextAppearance(style)
            } else {
                view.setTextAppearance(view.context, style)
            }
        }

        /**
         * Set tint color of current CheckBox button
         * @param chk current TextView
         * @param color current color resource
         */
        fun setCheckBoxTint(@NotNull chk: CheckBox, context: Context, @ColorRes color: Int) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                chk.buttonTintList = context.getColorStateList(color)
            } else {
                CompoundButtonCompat.setButtonTintList(
                    chk,
                    ColorStateList.valueOf(ContextCompat.getColor(context, color))
                )
            }
        }

        /**
         * Set tint color of current Button
         * @param btn current Button
         * @param style current style
         */
        @SuppressLint("ResourceType")
        fun setButtonStyle(@NotNull btn: Button, @NotNull context: Context, @StyleRes style: Int) {
            // order is very important !
            val attrs = intArrayOf(
                android.R.attr.textSize, //0
                android.R.attr.text, //1
                android.R.attr.textColor, //2
                android.R.attr.fontFamily, //3
                android.R.attr.backgroundTint //4
            )

            val ta: TypedArray = context.obtainStyledAttributes(style, attrs)

            val text =  ta.getText(1)
            val textColor = ta.getColor(2, Color.BLACK)
            val backgroundColor = ta.getColor(4, Color.BLACK)
            val fontId = ta.getResourceId(3, -1)

            val typeface = if (fontId > 0) {
                ResourcesCompat.getFont(context, fontId)
            } else {
                Typeface.DEFAULT
            }

            btn.style {
                text(text)
                textColor(textColor)
                backgroundTint(backgroundColor)
                fontFamily(typeface)
            }

            ta.recycle()
        }

    }
}