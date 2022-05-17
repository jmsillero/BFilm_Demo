package com.bacteria.bestfilm.presentation.main.ui.poster.adapter

import androidx.recyclerview.widget.GridLayoutManager

class GridSpanCalculator : GridLayoutManager.SpanSizeLookup() {
    override fun getSpanSize(position: Int): Int {
       return 1
    }

}