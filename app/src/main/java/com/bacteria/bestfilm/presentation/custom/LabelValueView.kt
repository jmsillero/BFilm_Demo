package com.bacteria.bestfilm.presentation.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.Drawable
import android.text.TextPaint
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.bacteria.bestfilm.R

/**
 * Label Value View is a visual representation of the
 * common Label-Value view pair
 */
class LabelValueView : LinearLayout {
    private var _label: String? = null
    private var _value: String? = null

    lateinit var tvLabel: TextView
    lateinit var tvValue: TextView
    lateinit var llMain: LinearLayout

    var label: String?
        get() = _label
        set(value) {
            _label = value
            initializeValues()
        }

    var value: String?
        get() = _value
        set(value) {
            _value = value
            initializeValues()
        }


    constructor(context: Context) : super(context) {
        init(null, 0)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs, 0)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs, defStyle)
    }

    private fun init(attrs: AttributeSet?, defStyle: Int) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.label_value_view, this)

        val a = context.obtainStyledAttributes(
            attrs, R.styleable.LabelValueView, defStyle, 0
        )
        setupUi(view)

        if (a.hasValue(R.styleable.LabelValueView_android_orientation)) {
            val orientation = a.getInt(R.styleable.LabelValueView_android_orientation, 0)
            llMain.orientation = orientation
        }

        label = a.getString(R.styleable.LabelValueView_label)

        a.recycle()
    }

    private fun setupUi(view: View) {
        tvLabel = view.findViewById(R.id.tv_label)
        tvValue = view.findViewById(R.id.tv_value)
        llMain = view.findViewById(R.id.ll_main)
        initializeValues()
    }

    private fun initializeValues() {
        label?.let {
            tvLabel.text = "$it: "
        }

        value?.let {
            tvValue.text = it
        }
    }


}