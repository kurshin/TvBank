package com.kurshin.tvbank.ui.privat.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.leanback.app.BrowseSupportFragment
import com.kurshin.tvbank.ui.privat.view_model.PrivatViewModel
import dagger.hilt.android.AndroidEntryPoint

import com.kurshin.tvbank.ui.privat.builder.AdapterBuilder
import com.kurshin.tvbank.util.*

import java.time.LocalDate


@AndroidEntryPoint
class PrivatFragment : BrowseSupportFragment() {

    private val viewModel: PrivatViewModel by viewModels()
    private val todayDate = LocalDate.now()
    private var balanceStr = ""
    private var dateStr = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI(todayDate)
        setupClickListeners()
        setupObservables()
        viewModel.getCurrentBalance()
    }

    fun onBackPressed(): Boolean {
        if (!isShowingHeaders) {
            startHeadersTransition(true)
            return true
        }

        return false
    }

    private fun setupUI(newDate: LocalDate) {
        dateStr = if (todayDate.year == newDate.year) {
            newDate.toDateStr()
        } else {
            newDate.toYearStr()
        }
        setupTitle()
        adapter = AdapterBuilder.buildMonthAdapter(newDate, resources)
    }

    private fun setupTitle() {
        title = if (balanceStr.isEmpty()) {
            dateStr
        } else {
            dateStr.toSpanTitle(balanceStr)
        }
    }

    private fun setupObservables() {
        viewModel.currentBalance.observe(viewLifecycleOwner) {
            it.info.cardbalance?.apply {
                balanceStr = "${balance.substring(0, balance.indexOf("."))} ${card.currency}"
                setupTitle()
            }
        }

        viewModel.requestError.observe(viewLifecycleOwner) {
            showDialog(it)
        }
    }

    private fun setupClickListeners() {
        setOnItemViewClickedListener { _, item, _, row ->
            onMonthClicked(row.id, item)
        }

        setOnSearchClickedListener {
            DialogYear.show(requireActivity()) { date ->
                setupUI(date)
            }
        }
    }

    private fun onMonthClicked(monthId: Long, dayData: Any) {

    }
}


