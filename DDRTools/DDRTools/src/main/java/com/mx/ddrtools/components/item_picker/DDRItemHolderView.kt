package com.mx.ddrtools.components.item_picker

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.mx.ddrtools.R
import com.mx.ddrtools.databinding.DdrItemHolderPickerBinding

class DDRItemHolderView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var viewBinding: DdrItemHolderPickerBinding = DdrItemHolderPickerBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {

        attrs?.let {
            val styledAttributes =
                context.obtainStyledAttributes(it, R.styleable.DDRItemHolderView, 0, 0)
            val textTitle =
                styledAttributes.getString(R.styleable.DDRItemHolderView_holderTextTitle)
            val textTitleSize = styledAttributes.getDimensionPixelSize(
                R.styleable.DDRItemHolderView_holderTextTitleSize,
                15
            )
            val textTitleFont =
                styledAttributes.getResourceId(R.styleable.DDRItemHolderView_holderTextTitleFont, 0)
            val textTitleColor = styledAttributes.getColor(
                R.styleable.DDRItemHolderView_holderTextTitleColor,
                0xFF808080.toInt()
            )
            val itemIcon =
                styledAttributes.getResourceId(R.styleable.DDRItemHolderView_holderIcon, 0)
            val itemIconColor = styledAttributes.getResourceId(
                R.styleable.DDRItemHolderView_holderIconColor,
                R.color.white
            )
            val itemBackground =
                styledAttributes.getResourceId(R.styleable.DDRItemHolderView_holderBackground, 0)


            setTitleText(textTitle)
            setItemIcon(itemIcon, itemIconColor)
            setTitleSize(textTitleSize)
            setTitleFont(textTitleFont)
            setTitleColor(textTitleColor)
            styledAttributes.recycle()
            setItemBackground(itemBackground)
        }
    }

    private fun setTitleText(value: String?) = with(viewBinding) {
        value?.let {
            tvItemTitle.text = it
        } ?: run {
            tvItemTitle.text = ""
        }
    }

    private fun setTitleSize(textSize: Int) = with(viewBinding) {
        tvItemTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize.toFloat())
    }

    private fun setTitleFont(resource: Int) = with(viewBinding) {
        if (resource != 0) {
            tvItemTitle.typeface = ResourcesCompat.getFont(context, resource)
        }
    }

    private fun setTitleColor(colorID: Int) = with(viewBinding) {
        tvItemTitle.setTextColor(colorID)
    }

    private fun setItemIcon(resource: Int, colorTint: Int) = with(viewBinding) {
        ivItem.setImageResource(resource)
        ivItem.setColorFilter(ContextCompat.getColor(context, colorTint))
    }

    private fun setItemBackground(resource: Int) {
        rootView.setBackgroundResource(resource)
    }
}