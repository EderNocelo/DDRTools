package com.mx.ddrtools.components.item_picker

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.airbnb.paris.extensions.style
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

            // ATTRS FOR TITLE
            val titleStyle = styledAttributes.getResourceId(
                R.styleable.DDRItemHolderView_holderTextStyle,
                R.style.DDRItemHolder_Title
            )

            val itemIcon = styledAttributes.getResourceId(R.styleable.DDRItemHolderView_holderIcon, 0)
            val itemIconColor = styledAttributes.getResourceId(R.styleable.DDRItemHolderView_holderIconColor, R.color.white)
            val itemBackground = styledAttributes.getResourceId(R.styleable.DDRItemHolderView_holderBackground, 0)

            setTitleStyle(titleStyle)
            setItemIcon(itemIcon, itemIconColor)
            setItemBackground(itemBackground)

            styledAttributes.recycle()
        }
    }

    fun setTitleStyle(@StyleRes titleStyle: Int) = viewBinding.apply { tvItemTitle.style(titleStyle) }

    fun setTitle(@StringRes title: Int) = viewBinding.apply { tvItemTitle.setText(title) }
    fun setTitle(title: String) = viewBinding.apply { tvItemTitle.text = title }

    fun setItemIcon(resource: Int, colorTint: Int) = with(viewBinding) {
        ivItem.setImageResource(resource)
        ivItem.setColorFilter(ContextCompat.getColor(context, colorTint))
    }

    fun setItemBackground(resource: Int) {
        rootView.setBackgroundResource(resource)
    }
}