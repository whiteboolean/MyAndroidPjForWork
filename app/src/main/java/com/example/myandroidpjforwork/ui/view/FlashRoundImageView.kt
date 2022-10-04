package com.example.myandroidpjforwork.ui.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.animation.LinearInterpolator
import androidx.appcompat.widget.AppCompatImageView


class FlashRoundImageView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attributeSet, defStyleAttr) {

    private  val TAG = "FlashRoundImageView"

    private var borderColor = 0 // 圆形头像的边框颜色

    private var borderWidth = 0// 圆形头像的边框宽度

    private var mBitmapPaint: Paint? = null// 绘制图像的Paint

    private var mBorderPaint: Paint? = null

    private var mMatrix: Matrix? = null // 图像矩阵,本身是一个3*3矩阵

    private var mRadius = 0

    private var mImgWidth = 0

    private var centerX = 0

    private var centerY = 0

    /***
     *
     * @param context
     * @param attrs
     */
    private var smallInnerScaleRatio = 1f //变小

    private var BigOutsideRatio = 1f

    init {
        initAttrs()
    }

    private fun initAttrs() {
        borderColor = Color.RED
        borderWidth = 8
        postDelayed({ startAnim() }, 2000)
        mBitmapPaint = Paint()
        mBorderPaint = Paint()
        mMatrix = Matrix()
        mBitmapPaint!!.isAntiAlias = true
        mBorderPaint!!.isAntiAlias = true
    }

    private fun startAnim() {
        val valueAnimator = ValueAnimator.ofFloat(1f, 0.5f)
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.duration = 500
        valueAnimator.repeatCount = -1
        valueAnimator.addUpdateListener { animation ->
            val value = animation.animatedValue as Float
            smallInnerScaleRatio = value
            BigOutsideRatio = 1 + (1 - value)
            invalidate()
        }
        valueAnimator.start()
    }


    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        //写好一个控件自定义View,写出一个能用的自定义View不易啊
        // 虽然测量这块儿寥寥几行代码,但是还得心细啊。。
        val imgHeight: Int =
            setMeasureHeight(heightMeasureSpec) - paddingTop - paddingBottom - borderWidth * 2
        val imgWidth: Int =
            setMeasureWidth(widthMeasureSpec) - paddingLeft - paddingRight - borderWidth * 2
        if (imgHeight < imgWidth) {
            mImgWidth = imgHeight
            mRadius = mImgWidth / 2
            centerX = mRadius
            centerY = mRadius
            Log.d("Round", "imgHeight < imgWidth")
        } else { //
            mImgWidth = imgWidth
            mRadius = mImgWidth / 4 //半径是图片的一半
            centerX = mImgWidth / 2
            centerY = centerX
            Log.d("Round", "imgHeight > imgWidth")
        }
        setMeasuredDimension(setMeasureWidth(widthMeasureSpec), setMeasureHeight(heightMeasureSpec))
    }

    private fun setMeasureHeight(heightMeasureSpec: Int): Int {
        var height = 0
        val minHeight = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 56f,
            resources.displayMetrics
        ).toInt()
        val specMode = MeasureSpec.getMode(heightMeasureSpec)
        val specSize = MeasureSpec.getSize(heightMeasureSpec)
        when (specMode) {
            MeasureSpec.EXACTLY -> height = if (specSize < minHeight) minHeight else specSize // 此处我是设置了EXACTLY的值，仅是圆形图片大小的值
            MeasureSpec.AT_MOST -> height = minHeight + paddingTop + paddingBottom
            MeasureSpec.UNSPECIFIED -> height = minHeight + paddingTop + paddingBottom
        }
        return height
    }

    private fun setMeasureWidth(widthMeasureSpec: Int): Int {
        var width = 0
        val minWidth = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP, 56f,
            resources.displayMetrics
        ).toInt()
        val specMode = MeasureSpec.getMode(widthMeasureSpec)
        val specSize = MeasureSpec.getSize(widthMeasureSpec)
        when (specMode) {
            MeasureSpec.EXACTLY -> width = (if (specSize < minWidth) minWidth else specSize)
            MeasureSpec.AT_MOST -> width = minWidth + getPaddingLeft() + getPaddingRight()
            MeasureSpec.UNSPECIFIED -> width = minWidth + getPaddingRight() + getPaddingLeft()
        }
        return width
    }

    override fun onDraw(canvas: Canvas) {
        this.setBackgroundColor(Color.WHITE)
        if (drawable == null) {
            return
        }
        setShader()
        val realRadiusBitmap = mRadius * smallInnerScaleRatio //修改图片绘制的半径，而缩放
        //中心点
        val centerTempx: Float = (centerX + borderWidth + paddingLeft).toFloat()
        val centerTempy: Float = (centerY + borderWidth + paddingTop).toFloat()
        canvas.drawCircle(
            centerTempx, centerTempy,
            realRadiusBitmap, mBitmapPaint!!
        ) //绘制头像
        mBorderPaint!!.color = borderColor
        mBorderPaint!!.style = Paint.Style.STROKE
        mBorderPaint!!.strokeWidth = (borderWidth / 2).toFloat()
        canvas.drawCircle(
            (centerX + borderWidth + paddingLeft).toFloat(),
            (centerY + borderWidth + paddingTop).toFloat(),
            (
                    mRadius + borderWidth / 2).toFloat(),
            mBorderPaint!!
        ) //绘制外圆
        val realRadiusOut = mRadius * BigOutsideRatio
        mBorderPaint!!.strokeWidth = (borderWidth / 2).toFloat()
        mBorderPaint!!.color = Color.GREEN
        canvas.drawCircle(
            (centerX + borderWidth + paddingLeft).toFloat(),
            (centerY + borderWidth + paddingTop).toFloat(),
            realRadiusOut + borderWidth / 2,
            mBorderPaint!!
        ) //绘制散动的圆
    }

    /**
     * 初始化bitmapShader
     * 图片缩放，通过矩阵进行。
     * 在于矩阵先缩放，再移动
     */
    private fun setShader() {
        val drawable: Drawable = drawable
        val bmp = drawableToBitmap(drawable)
        val mBitmapShader = BitmapShader(bmp, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        var scale = 1.0f
        // 去取bitmap中宽度和高度中更小的，为了使图像缩放之后，可以填充满控件的空间，
        // 此处切记要乘以1.0f，，这种低级错误写的时候又犯了一次。
        scale = mImgWidth * 1.0f / Math.min(bmp.width, bmp.height)
        //scale=scale*currentScaleRatio;
        mMatrix!!.setScale(scale, scale)
        mMatrix!!.postTranslate(
            (paddingLeft + borderWidth).toFloat(),
            (paddingTop + borderWidth).toFloat()
        ) //这里使用了Matrix的后乘进行效果叠加，
        // 使图像根据padding进行位移
        mBitmapShader.setLocalMatrix(mMatrix)
        mBitmapPaint!!.shader = mBitmapShader
    }

    /**
     * 将Drawable转变为bitmap
     */
    private fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
        val h = drawable.intrinsicHeight
        val w = drawable.intrinsicWidth
        val bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap) // 建立对应的bitmap画布
        drawable.setBounds(0, 0, w, h) // 此处的setBounds是指，drawable将在canvas的0,0,w,h矩形区域内
        drawable.draw(canvas) // 将drawable的内容画到画布中去
        return bitmap
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d(TAG, "onDetachedFromWindow: ")
    }


}