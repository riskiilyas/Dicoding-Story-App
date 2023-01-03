package com.keecoding.storyapp.view

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.keecoding.storyapp.R

class NameEditText: AppCompatEditText {

    private lateinit var clearButtonImage: Drawable
    private lateinit var nameIcon: Drawable
    private var paint = Paint()
    var isReady = false

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
//        clearButtonImage = ContextCompat.getDrawable(context, R.drawable.ic_baseline_close_24) as Drawable
//        clearButtonImage.setTint(ContextCompat.getColor(context, R.color.default_text))
        paint.style = Paint.Style.STROKE
        paint.color = ContextCompat.getColor(context, R.color.default_text)
        nameIcon = ContextCompat.getDrawable(context, R.drawable.ic_baseline_person_24) as Drawable
        nameIcon.setTint(ContextCompat.getColor(context, R.color.default_text))
        inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
        compoundDrawablePadding = 16

        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                setHint(R.string.name)
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.isEmpty()) {
                    isReady = false
                    error = resources.getString(R.string.name_is_empty)
                } else {
                    isReady = true
                }
                isReady = s.isNotEmpty()
            }
            override fun afterTextChanged(s: Editable) {
                // Do nothing.
            }
        })
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        hint = resources.getString(R.string.your_name)
//        canvas.drawRoundRect(0F, 0F, x, y, 16F, 16F, paint)
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
        background = ContextCompat.getDrawable(context, R.drawable.password_et)
        setButtonDrawables(startOfTheText = nameIcon)
//        setButtonDrawables(endOfTheText = clearButtonImage)
    }

    private fun setButtonDrawables(
        startOfTheText: Drawable? = null,
        topOfTheText:Drawable? = null,
        endOfTheText:Drawable? = null,
        bottomOfTheText: Drawable? = null
    ){
        setCompoundDrawablesWithIntrinsicBounds(
            startOfTheText,
            topOfTheText,
            endOfTheText,
            bottomOfTheText
        )
    }

}