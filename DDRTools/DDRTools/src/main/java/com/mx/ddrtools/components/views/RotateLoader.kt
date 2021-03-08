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

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.mx.ddrtools.R

class RotateLoader constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
): View(context, attrs, defStyleAttr) {

    private val DEFAULT_WIDTH = 6
    private val DEFAULT_SHADOW_POSITION = 2
    private val DEFAULT_SPEED_OF_DEGREE = 10

    private var mPaint: Paint? = null

    private var loadingRectF: RectF? = null
    private var shadowRectF: RectF? = null

    private var topDegree = 10
    private var bottomDegree = 190

    private var arc = 0f


    private var changeBigger = true

    private var shadowPosition = 0

    private var isStart = false

    private var speedOfDegree = 0

    private var speedOfArc = 0f

    private var colorRes = 0
    private var strokeWidth = 0
    private var autoPlay = false

    constructor(context: Context, attrs: AttributeSet) : this(context, attrs, 0) {}

    /**
     * Init component
     * */
    init {
        // search for attrs
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RotateLoader)
            // assign color resource from attrs
            colorRes = typedArray.getResourceId(R.styleable.RotateLoader_rotateColor, R.color.white)
            // assign stroke width from attrs
            strokeWidth = typedArray.getDimensionPixelSize(
                R.styleable.RotateLoader_rotateStrokeWidth,
                dpToPx(context, DEFAULT_WIDTH.toFloat())
            )
            // assign shadow position resource from attrs
            shadowPosition = typedArray.getInt(
                R.styleable.RotateLoader_rotateShadowPosition,
                DEFAULT_SHADOW_POSITION
            )
            // assign speed from attrs
            speedOfDegree =
                typedArray.getInt(R.styleable.RotateLoader_rotateSpeed, DEFAULT_SPEED_OF_DEGREE)
            // get autoPlay from attrs
            autoPlay =
                typedArray.getBoolean(R.styleable.RotateLoader_rotateAutoPlay, false)
            typedArray.recycle()
            return@let
        }?:run{
            // assign default color
            colorRes = R.color.white
            // assign default stroke width
            strokeWidth = dpToPx(context, DEFAULT_WIDTH.toFloat())
            // assign default shadow position
            shadowPosition = dpToPx(getContext(), DEFAULT_SHADOW_POSITION.toFloat())
            // assign default speed
            speedOfDegree = DEFAULT_SPEED_OF_DEGREE
            // get autoPlay by default
            autoPlay = false
        }

        speedOfArc = (speedOfDegree / 4).toFloat()
        mPaint = Paint()
        mPaint!!.color = ContextCompat.getColor(context, colorRes)
        mPaint!!.isAntiAlias = true
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.strokeWidth = strokeWidth.toFloat()
        mPaint!!.strokeCap = Paint.Cap.ROUND

        if (autoPlay){
            start()
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        arc = 10f
        loadingRectF = RectF(
            (2 * strokeWidth).toFloat(),
            (2 * strokeWidth).toFloat(),
            (w - 2 * strokeWidth).toFloat(),
            (h - 2 * strokeWidth).toFloat()
        )
        shadowRectF = RectF(
            (2 * strokeWidth + shadowPosition).toFloat(),
            (2 * strokeWidth + shadowPosition).toFloat(),
            (w - 2 * strokeWidth + shadowPosition).toFloat(),
            (h - 2 * strokeWidth + shadowPosition).toFloat()
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (!isStart) {
            return
        }
        mPaint!!.color = Color.parseColor("#1a000000")
        canvas.drawArc(shadowRectF!!, topDegree.toFloat(), arc, false, mPaint!!)
        canvas.drawArc(shadowRectF!!, bottomDegree.toFloat(), arc, false, mPaint!!)
        mPaint!!.color = ContextCompat.getColor(context, colorRes)
        canvas.drawArc(loadingRectF!!, topDegree.toFloat(), arc, false, mPaint!!)
        canvas.drawArc(loadingRectF!!, bottomDegree.toFloat(), arc, false, mPaint!!)
        topDegree += speedOfDegree
        bottomDegree += speedOfDegree

        if (topDegree > 360) {
            topDegree -= 360
        }
        if (bottomDegree > 360) {
            bottomDegree -= 360
        }
        if (changeBigger) {
            if (arc < 160) {
                arc += speedOfArc
                invalidate()
            }
        } else {
            if (arc > speedOfDegree) {
                arc -= 2 * speedOfArc
                invalidate()
            }
        }
        if (arc >= 160 || arc <= 10) {
            changeBigger = !changeBigger
            invalidate()
        }
    }

    fun start() {
        startAnimator()
        isStart = true
        invalidate()
    }

    fun stop() {
        stopAnimator()
        invalidate()
    }

    fun isStarted() = isStart

    fun setLoaderColor(@ColorRes color: Int) {
        colorRes = color
    }

    private fun startAnimator() {
        val scaleXAnimator = ObjectAnimator.ofFloat(this, "scaleX", 0.0f, 1f)
        val scaleYAnimator = ObjectAnimator.ofFloat(this, "scaleY", 0.0f, 1f)
        scaleXAnimator.duration = 300
        scaleXAnimator.interpolator = LinearInterpolator()
        scaleYAnimator.duration = 300
        scaleYAnimator.interpolator = LinearInterpolator()
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator)
        animatorSet.start()
    }

    private fun stopAnimator() {
        val scaleXAnimator = ObjectAnimator.ofFloat(this, "scaleX", 1f, 0f)
        val scaleYAnimator = ObjectAnimator.ofFloat(this, "scaleY", 1f, 0f)
        scaleXAnimator.duration = 300
        scaleXAnimator.interpolator = LinearInterpolator()
        scaleYAnimator.duration = 300
        scaleYAnimator.interpolator = LinearInterpolator()
        val animatorSet = AnimatorSet()
        animatorSet.playTogether(scaleXAnimator, scaleYAnimator)
        animatorSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {}
            override fun onAnimationEnd(animation: Animator) {
                isStart = false
            }

            override fun onAnimationCancel(animation: Animator) {}
            override fun onAnimationRepeat(animation: Animator) {}
        })
        animatorSet.start()
    }

    fun dpToPx(context: Context, dpVal: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dpVal,
            context.resources.displayMetrics
        )
            .toInt()
    }
}