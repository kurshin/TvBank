package com.kurshin.tvbank.ui.privat.builder

import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import com.kurshin.tvbank.ui.privat.presenter.DayPresenter
import java.text.DateFormatSymbols

object AdapterBuilder {

    fun buildMonthAdapter(): ArrayObjectAdapter {
        val result = ArrayObjectAdapter(ListRowPresenter())

        for (i in 0..11) {
            val (listRowAdapter, headerItem) = getMonthData(i)
            result.add(ListRow(headerItem, listRowAdapter))
        }

        return result
    }

    private fun getMonthData(monthIndex: Int):  RowMonthData {
        val listRowAdapter = ArrayObjectAdapter(DayPresenter())
        listRowAdapter.add("Month")
        listRowAdapter.add("01 Sat")
        listRowAdapter.add("02 Sun")
        val header = HeaderItem(monthIndex.toLong(), DateFormatSymbols().months[monthIndex])
        return RowMonthData(listRowAdapter, header)
    }
}

data class RowMonthData(val listRowAdapter: ArrayObjectAdapter, val header: HeaderItem)