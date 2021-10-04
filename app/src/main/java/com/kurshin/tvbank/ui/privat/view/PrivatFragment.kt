package com.kurshin.tvbank.ui.privat.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.leanback.app.BrowseSupportFragment
import com.kurshin.tvbank.ui.privat.view_model.PrivatViewModel
import dagger.hilt.android.AndroidEntryPoint

import com.kurshin.tvbank.ui.privat.builder.AdapterBuilder

import com.kurshin.tvbank.util.DialogYear
import com.kurshin.tvbank.util.toDateStr
import com.kurshin.tvbank.util.toYearStr
import java.time.LocalDate


@AndroidEntryPoint
class PrivatFragment : BrowseSupportFragment() {

    private val viewModel: PrivatViewModel by viewModels()
    private val todayDate = LocalDate.now()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI(todayDate)

        setOnItemViewClickedListener { _, item, _, row ->
            onMonthClicked(row.id, item)
        }

        setOnSearchClickedListener {
            DialogYear.show(requireActivity()) { date ->
                setupUI(date)
            }
        }
    }

    fun onBackPressed(): Boolean {
        if (!isShowingHeaders) {
            startHeadersTransition(true)
            return true
        }

        return false
    }

    private fun setupUI(newDate: LocalDate) {
        title = if (todayDate.year == newDate.year) {
            newDate.toDateStr()
        } else {
            newDate.toYearStr()
        }
        adapter = AdapterBuilder.buildMonthAdapter(newDate, resources)
    }

    private fun onMonthClicked(monthId: Long, dayData: Any) {

    }
}


