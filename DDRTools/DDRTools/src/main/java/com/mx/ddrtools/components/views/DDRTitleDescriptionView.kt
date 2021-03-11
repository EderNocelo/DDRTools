package com.mx.ddrtools.components.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.StringRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.paris.extensions.style
import com.mx.ddrtools.R
import com.mx.ddrtools.databinding.DdrItemTitleDescriptionBinding

class DDRTitleDescriptionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private var viewBinding: DdrItemTitleDescriptionBinding =
        DdrItemTitleDescriptionBinding.inflate(
            LayoutInflater.from(context), this, true
        )

    init {

        attrs?.let {
            val styledAttributes =
                context.obtainStyledAttributes(it, R.styleable.DDRTitleDescriptionView, 0, 0)

            // ATTRS FOR TITLE
            val titleStyle = styledAttributes.getResourceId(
                R.styleable.DDRTitleDescriptionView_titleTextStyle,
                R.style.DDRItemTitleDescription_Title
            )
            val titleValue = styledAttributes.getResourceId(
                R.styleable.DDRTitleDescriptionView_titleText,
                R.string.ddr_common_title
            )

            // ATTRS FOR SUBTITLE
            val subtitleStyle = styledAttributes.getResourceId(
                R.styleable.DDRTitleDescriptionView_subtitleTextStyle,
                R.style.DDRItemTitleDescription_Subtitle
            )
            val subtitleValue = styledAttributes.getResourceId(
                R.styleable.DDRTitleDescriptionView_subtitleText,
                R.string.ddr_common_subtitle
            )

            // apply styles
            setTitleStyle(titleStyle)
            setSubtitleStyle(subtitleStyle)
            setTitle(titleValue)
            setSubtitle(subtitleValue)

            styledAttributes.recycle()
        }
    }

    fun setTitleStyle(@StyleRes titleStyle: Int) = viewBinding.apply { tvTitle.style(titleStyle) }
    fun setSubtitleStyle(@StyleRes subtitleStyle: Int) = viewBinding.apply { tvSubtitle.style(subtitleStyle) }

    fun setTitle(@StringRes title: Int) = viewBinding.apply { tvTitle.setText(title) }
    fun setTitle(title: String) = viewBinding.apply { tvTitle.text = title }

    fun setSubtitle(@StringRes subtitle: Int) = viewBinding.apply { tvSubtitle.setText(subtitle) }
    fun setSubtitle(subtitle: String) = viewBinding.apply { tvSubtitle.text = subtitle }
}