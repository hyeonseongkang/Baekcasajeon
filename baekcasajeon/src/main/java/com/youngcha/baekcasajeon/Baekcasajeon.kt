package com.youngcha.baekcasajeon

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ReplacementSpan
import android.view.View
import android.widget.TextView

data class KeywordOptions(
    val nonSelectedTextColor: Int,  // 선택되지 않은 상태의 텍스트 색상
    val selectedTextColor: Int, // 선택된 상태의 텍스트 색상
    val nonSelectedBackgroundColor: Int,  // 선택되지 않은 상태의 배경 색상
    val selectedBackgroundColor: Int,  // 선택된 상태의 배경 색상
    val padding: Rect = Rect(0, 0, 0, 0),  // 키워드 주위의 패딩 (왼쪽, 위, 오른쪽, 아래 순)
    val cornerRadius: Float = 0f,  // 배경의 라운드된 모서리의 반경
    val isBold: Boolean = false  // 텍스트를 굵게 표시할지 여부
)

// 확장 함수: TextView의 텍스트에서 특정 키워드를 찾아 스타일 및 클릭 가능한 스팬을 적용합니다.
fun TextView.baekcasajeon(
    optionsMap: Map<String, KeywordOptions>,
    onKeywordClicked: (String) -> Unit
): Map<String, AnimatedRoundedBackgroundSpan> {
    val spannable = SpannableStringBuilder(text)
    val spanMap = mutableMapOf<String, AnimatedRoundedBackgroundSpan>()

    optionsMap.forEach { (keyword, option) ->
        var start = text.indexOf(keyword)
        while (start != -1) {
            val span = AnimatedRoundedBackgroundSpan(
                option.nonSelectedTextColor,
                option.selectedTextColor,
                option.nonSelectedBackgroundColor,
                option.selectedBackgroundColor,
                option.padding,
                option.cornerRadius,
                this,
                option.isBold
            )
            spanMap[keyword] = span

            spannable.setSpan(span, start, start + keyword.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            spannable.setSpan(object : ClickableSpan() {
                override fun onClick(widget: View) {
                    if (span.toggleSelectedAndCheck()) {
                        onKeywordClicked(keyword)
                    }
                }
            }, start, start + keyword.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            start = text.indexOf(keyword, start + keyword.length)
        }
    }

    this.text = spannable
    this.movementMethod = LinkMovementMethod.getInstance()

    return spanMap
}


// 사용자 정의 스팬: 클릭 가능한 키워드에 대한 배경 및 스타일을 조정합니다.
class AnimatedRoundedBackgroundSpan(
    private val nonSelectedTextColor: Int,
    private val selectedTextColor: Int,
    private val nonSelectedBackgroundColor: Int,
    private val selectedBackgroundColor: Int,
    private val padding: Rect,
    private val cornerRadius: Float,
    private val view: View,
    private val isBold: Boolean
) : ReplacementSpan() {

    private var isSelected: Boolean = false
    private val currentBackgroundColor get() = if (isSelected) selectedBackgroundColor else nonSelectedBackgroundColor
    private val currentTextColor get() = if (isSelected) selectedTextColor else nonSelectedTextColor

    // 선택 상태를 전환하고 람다 호출이 필요한지 여부를 반환합니다.
    fun toggleSelectedAndCheck(): Boolean {
        isSelected = !isSelected
        view.postInvalidate()
        return isSelected  // 선택된 상태일 때만 true를 반환합니다.
    }
    override fun getSize(
        paint: Paint,
        text: CharSequence?,
        start: Int,
        end: Int,
        fm: Paint.FontMetricsInt?
    ): Int {
        return (paint.measureText(text, start, end) + padding.left + padding.right).toInt()
    }

    // 텍스트와 배경을 Canvas에 그립니다.
    override fun draw(
        canvas: Canvas,
        text: CharSequence,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        y: Int,
        bottom: Int,
        paint: Paint
    ) {
        val width = paint.measureText(text, start, end)
        val rect = RectF(
            x,
            top.toFloat() - padding.top,
            x + width + padding.left + padding.right,
            bottom.toFloat() + padding.bottom
        )

        // 라운드된 배경을 그립니다.
        paint.color = currentBackgroundColor
        canvas.drawRoundRect(rect, cornerRadius, cornerRadius, paint)

        // 텍스트를 그립니다.
        if (isBold) {
            paint.typeface = Typeface.create(paint.typeface, Typeface.BOLD)
        } else {
            paint.typeface = Typeface.create(paint.typeface, Typeface.NORMAL)
        }
        paint.color = currentTextColor
        canvas.drawText(text.subSequence(start, end).toString(), x + padding.left, y.toFloat(), paint)
    }

    // 선택 상태를 전환합니다.
    fun toggleSelected() {
        isSelected = !isSelected
        view.postInvalidate()
    }
}
