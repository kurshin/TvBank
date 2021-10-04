package com.kurshin.tvbank.ui.privat.presenter

import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.leanback.widget.Presenter
import com.kurshin.tvbank.R
import com.kurshin.tvbank.util.toDayStr
import java.time.LocalDate

class DayPresenter : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = TextView(parent.context)
        val padding = parent.resources.getDimensionPixelSize(R.dimen.cell_day_margin)
        val height = parent.resources.getDimensionPixelSize(R.dimen.cell_day_height)

        view.layoutParams = ViewGroup.LayoutParams(WRAP_CONTENT, height)
        view.setPadding(padding, 0, padding, 0)
        view.minWidth = parent.resources.getDimensionPixelSize(R.dimen.cell_day_width)
        view.isFocusable = true
        view.isFocusableInTouchMode = true
        view.setBackgroundColor(ContextCompat.getColor(parent.context, R.color.test_background))
        view.setTextColor(Color.WHITE)
        view.gravity = Gravity.CENTER
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any) {
        val day = item as DayData
        (viewHolder.view as TextView).text = day.getText()
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {}
}

data class DayData(val type: Type = Type.DAY, val title: String = "", val day: LocalDate = LocalDate.MIN) {

    fun isDay() = type == Type.DAY

    fun getText(): String {
        return if (isDay()) day.toDayStr() else title
    }

    enum class Type {
        FULL_MONTH, DAY
    }
}