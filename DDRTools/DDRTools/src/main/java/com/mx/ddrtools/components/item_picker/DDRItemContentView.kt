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
import com.mx.ddrtools.databinding.DdrItemContentPickerBinding

class DDRItemContentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var viewBinding: DdrItemContentPickerBinding = DdrItemContentPickerBinding.inflate(
        LayoutInflater.from(context), this, true
    )

    init {

        attrs?.let {
            val styledAttributes =
                context.obtainStyledAttributes(it, R.styleable.DDRItemContentView, 0, 0)

            // ATTRS FOR TITLE
            val titleStyle = styledAttributes.getResourceId(
                R.styleable.DDRItemContentView_itemTitleTextStyle,
                R.style.DDRItemContent_Title
            )
            val titleValue = styledAttributes.getResourceId(
                R.styleable.DDRItemContentView_itemTitleText,
                R.string.ddr_common_title
            )

            // ATTRS FOR SUBTITLE
            val subtitleStyle = styledAttributes.getResourceId(
                R.styleable.DDRItemContentView_itemSubtitleTextStyle,
                R.style.DDRItemContent_Subtitle
            )
            val subtitleValue = styledAttributes.getResourceId(
                R.styleable.DDRItemContentView_itemSubtitleText,
                R.string.ddr_common_subtitle
            )

            val itemIcon = styledAttributes.getResourceId(R.styleable.DDRItemContentView_itemIcon, 0)
            val itemIconColor = styledAttributes.getResourceId(R.styleable.DDRItemContentView_itemIconColor, R.color.black)
            val itemBackground = styledAttributes.getResourceId(R.styleable.DDRItemContentView_itemBackground, 0)

            setTitleStyle(titleStyle)
            setSubtitleStyle(subtitleStyle)
            setTitle(titleValue)
            setSubtitle(subtitleValue)
            setItemIcon(itemIcon, itemIconColor)
            setItemBackground(itemBackground)

            styledAttributes.recycle()
        }
    }

    fun setTitleStyle(@StyleRes titleStyle: Int) = viewBinding.apply { tvItemTitle.style(titleStyle) }
    fun setSubtitleStyle(@StyleRes subtitleStyle: Int) = viewBinding.apply { tvItemSubTitle.style(subtitleStyle) }

    fun setTitle(@StringRes title: Int) = viewBinding.apply { tvItemTitle.setText(title) }
    fun setTitle(title: String) = viewBinding.apply { tvItemTitle.text = title }

    fun setSubtitle(@StringRes subtitle: Int) = viewBinding.apply { tvItemSubTitle.setText(subtitle) }
    fun setSubtitle(subtitle: String) = viewBinding.apply { tvItemSubTitle.text = subtitle }

    fun setItemIcon(resource: Int, colorTint: Int) = with(viewBinding) {
        ivItem.setImageResource(resource)
        ivItem.setColorFilter(ContextCompat.getColor(context, colorTint))
    }

    fun setItemBackground(resource: Int) {
        rootView.setBackgroundResource(resource)
    }
}