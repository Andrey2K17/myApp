package com.pg13.myapp.ui.recyclerView

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class HorizontalSpaceItemDecoration(private val mVerticalSpaceHeight: Int) :
    ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, itemPosition: Int, parent: RecyclerView) {
        if (parent.adapter != null && itemPosition != parent.adapter!!.itemCount - 1) {
            outRect.right = mVerticalSpaceHeight
        }
    }
}