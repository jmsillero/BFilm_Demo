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
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bacteria.bestfilm.R
import com.bumptech.glide.Glide

/**
 * Custom views represent each item film
 *
 * Compounded by and image on top and a text below the image
 * */
class FilmItemView : LinearLayout {

    // the name of the film
    private var _label: String? = null

    // cover image representing the film
    private var _image: String? = null

    // view that shows the cover image
    lateinit var ivImage: ImageView

    // view that shows the film title
    lateinit var tvLabel: TextView

    var label: String?
        get() = _label
        set(value) {
            _label = value
            updateValues()
        }

    var image: String?
        get() = _image
        set(value) {
            _image = value
            updateValues()
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
        val view = inflater.inflate(R.layout.film_item_view, this)
        setupUi(view)

    }

    private fun setupUi(v: View) {
        ivImage = v.findViewById(R.id.iv_image)
        tvLabel = v.findViewById(R.id.tv_label)
        updateValues()
    }

    /**
     * Update the UI with the fields values
     * */
    private fun updateValues() {
        label?.let {
            tvLabel.text = it
        }

        image?.let {
            Glide.with(context)
                .load(it)
                .into(ivImage)
        }
    }


}