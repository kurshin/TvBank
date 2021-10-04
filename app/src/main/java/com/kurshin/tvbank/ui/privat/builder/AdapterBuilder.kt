package com.kurshin.tvbank.ui.privat.builder

import android.content.res.Resources
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import com.kurshin.tvbank.R
import com.kurshin.tvbank.ui.privat.presenter.DayData
import com.kurshin.tvbank.ui.privat.presenter.DayPresenter
import java.time.LocalDate

object AdapterBuilder {

    private val dateNow = LocalDate.now()

    fun buildMonthAdapter(date: LocalDate, resources: Resources): ArrayObjectAdapter {
        val result = ArrayObjectAdapter(ListRowPresenter())

        for (i in date.monthValue downTo 1) {
            val (listRowAdapter, headerItem) = getMonthData(i, date, resources.getString(R.string.full_month))
            result.add(ListRow(headerItem, listRowAdapter))
        }

        return result
    }

    private fun getMonthData(monthIndex: Int, date: LocalDate, fullMonthStr: String):  RowMonthData {
        val listRowAdapter = ArrayObjectAdapter(DayPresenter())
        listRowAdapter.add(DayData(DayData.Type.FULL_MONTH, fullMonthStr))

        var tempCalendar = LocalDate.of(date.year, monthIndex, 1)
        val monthName = tempCalendar.month.name

        val endMonthDay = if (dateNow.monthValue == monthIndex && dateNow.year == date.year) {
            date.dayOfMonth
        } else {
            tempCalendar.lengthOfMonth()
        }

        for (day in 1..endMonthDay) {
            listRowAdapter.add(DayData(day = tempCalendar))
            tempCalendar = tempCalendar.plusDays(1)
        }

        val header = HeaderItem(monthIndex.toLong(), monthName)
        return RowMonthData(listRowAdapter, header)
    }
}

data class RowMonthData(val listRowAdapter: ArrayObjectAdapter, val header: HeaderItem)