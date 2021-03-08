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
package com.mx.ddrtools.components.views

import android.content.Context
import android.util.AttributeSet
import android.util.DisplayMetrics
import androidx.recyclerview.widget.RecyclerView
import com.mx.ddrtools.R

class LimitedRecyclerView : RecyclerView {

    private var customMaxHeight: Int = 0

    constructor(context: Context) :
            super(context)

    constructor(context: Context, attrs: AttributeSet?) :
            super(context, attrs){
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.LimitedRecyclerView
        )
        customMaxHeight = a.getInt(
            R.styleable.LimitedRecyclerView_maxHeightLimit,
            customMaxHeight
        )
        a.recycle()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) :
            super(context, attrs, defStyleAttr) {
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.LimitedRecyclerView
        )
        customMaxHeight = a.getInt(
            R.styleable.LimitedRecyclerView_maxHeightLimit,
            customMaxHeight
        )
        a.recycle()
    }

    private fun convertDpToPixel(dp: Int): Int {
        return (dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT)).toInt()
    }

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        var heightSpecT = heightSpec
        if (customMaxHeight != 0){
            heightSpecT =
                MeasureSpec.makeMeasureSpec(convertDpToPixel(customMaxHeight), MeasureSpec.AT_MOST)
        }
        super.onMeasure(widthSpec, heightSpecT)
    }
}