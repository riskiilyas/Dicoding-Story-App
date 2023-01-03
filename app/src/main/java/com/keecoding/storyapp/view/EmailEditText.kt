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
import android.util.Patterns
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.keecoding.storyapp.R

class EmailEditText: AppCompatEditText {

    private lateinit var clearButtonImage: Drawable
    private lateinit var emailIcon: Drawable
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
        emailIcon = ContextCompat.getDrawable(context, R.drawable.ic_baseline_email_24) as Drawable
        emailIcon.setTint(ContextCompat.getColor(context, R.color.default_text))
        inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        compoundDrawablePadding = 16


        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                setHint(R.string.email)
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if(s.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(s).matches()) {
                  isReady = true
                } else {
                    isReady = false
                    error = resources.getString(R.string.email_is_invalid)
                }
            }
            override fun afterTextChanged(s: Editable) {
                // Do nothing.
            }
        })
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        hint = resources.getString(R.string.email)
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
        background = ContextCompat.getDrawable(context, R.drawable.password_et)
        setButtonDrawables(startOfTheText = emailIcon)
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