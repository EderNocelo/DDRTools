package com.mx.ddrtools.components.item_picker

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
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
        View.inflate(context, R.layout.ddr_item_content_picker, this)

        attrs?.let {
            val styledAttributes =
                context.obtainStyledAttributes(it, R.styleable.DDRItemContentView, 0, 0)
            // ATTRS FOR TITLE
            val textTitle = styledAttributes.getString(R.styleable.DDRItemContentView_itemTextTitle)
            val textTitleSize = styledAttributes.getDimensionPixelSize(
                R.styleable.DDRItemContentView_itemTextTitleSize,
                15
            )
            val textTitleFont =
                styledAttributes.getResourceId(R.styleable.DDRItemContentView_itemTextTitleFont, 0)
            val textTitleColor = styledAttributes.getColor(
                R.styleable.DDRItemContentView_itemTextTitleColor,
                0xFF808080.toInt()
            )

            // ATTRS FOR TITLE
            val textSubTitle =
                styledAttributes.getString(R.styleable.DDRItemContentView_itemTextSubTitle)
            val textSubTitleSize = styledAttributes.getDimensionPixelSize(
                R.styleable.DDRItemContentView_itemTextSubTitleSize,
                15
            )
            val textSubTitleFont =
                styledAttributes.getResourceId(
                    R.styleable.DDRItemContentView_itemTextSubTitleFont,
                    0
                )
            val textSubTitleColor = styledAttributes.getColor(
                R.styleable.DDRItemContentView_itemTextSubTitleColor,
                0xFF808080.toInt()
            )


            val itemIcon =
                styledAttributes.getResourceId(R.styleable.DDRItemContentView_itemIcon, 0)
            val itemIconColor = styledAttributes.getResourceId(
                R.styleable.DDRItemContentView_itemIconColor,
                R.color.black
            )
            val itemBackground =
                styledAttributes.getResourceId(R.styleable.DDRItemContentView_itemBackground, 0)


            setLabelsText(textTitle, textSubTitle)
            setLabelsSize(textTitleSize, textSubTitleSize)
            setLabelsFont(textTitleFont, textSubTitleFont)
            setLabelsColor(textTitleColor, textSubTitleColor)


            setItemIcon(itemIcon, itemIconColor)
            styledAttributes.recycle()
            setItemBackground(itemBackground)
        }
    }

    private fun setLabelsText(title: String?, subtitle: String?) = with(viewBinding) {
        title?.let {
            tvItemTitle.text = it
        } ?: run {
            tvItemTitle.text = ""
        }
        subtitle?.let {
            tvItemSubTitle.text = it
        } ?: run {
            tvItemSubTitle.text = ""
        }
    }

    private fun setLabelsSize(titleSize: Int, subtitleSize: Int) = with(viewBinding) {
        tvItemTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize.toFloat())
        tvItemSubTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, subtitleSize.toFloat())
    }

    private fun setLabelsFont(titleFont: Int, subtitleFont: Int) = with(viewBinding) {
        if (titleFont != 0 && subtitleFont != 0) {
            tvItemTitle.typeface = ResourcesCompat.getFont(context, titleFont)
            tvItemSubTitle.typeface = ResourcesCompat.getFont(context, subtitleFont)
        }
    }

    private fun setLabelsColor(titleColor: Int, subtitleColor: Int) = with(viewBinding) {
        tvItemTitle.setTextColor(titleColor)
        tvItemSubTitle.setTextColor(subtitleColor)
    }

    private fun setItemIcon(resource: Int, colorTint: Int) = with(viewBinding) {
        ivItem.setImageResource(resource)
        ivItem.setColorFilter(ContextCompat.getColor(context, colorTint))
    }

    private fun setItemBackground(resource: Int) {
        rootView.setBackgroundResource(resource)
    }

    fun setSubtitle(name: String) = with(viewBinding) {
        tvItemSubTitle.text = name
    }
}