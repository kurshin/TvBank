package com.kurshin.tvbank.ui.privat24.calendar.view

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.leanback.app.BrowseSupportFragment
import androidx.navigation.fragment.findNavController
import com.kurshin.tvbank.R
import com.kurshin.tvbank.ui.privat24.calendar.view_model.PrivatViewModel
import dagger.hilt.android.AndroidEntryPoint

import com.kurshin.tvbank.ui.privat24.calendar.builder.AdapterBuilder
import com.kurshin.tvbank.ui.privat24.calendar.presenter.DayData
import com.kurshin.tvbank.ui.privat24.day_detail.view.DayDetailFragment
import com.kurshin.tvbank.util.*

import java.time.LocalDate


@AndroidEntryPoint
class PrivatFragment : BrowseSupportFragment() {

    private val viewModel: PrivatViewModel by viewModels()
    private val todayDate = LocalDate.now()
    private lateinit var selectedDate: LocalDate
    private var balanceStr = ""
    private var dateStr = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (::selectedDate.isInitialized) {
            setupUI(selectedDate)
        } else {
            setupUI(todayDate)
        }
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

    private fun setupTitle() {
        title = if (balanceStr.isEmpty()) {
            dateStr
        } else {
            dateStr.toSpanTitle(balanceStr)
        }
    }

    private fun setupClickListeners() {
        setOnItemViewClickedListener { _, item, _, row ->
            onMonthClicked(item as DayData)
        }

        setOnSearchClickedListener {
            DialogYear.show(requireActivity()) { date ->
                selectedDate = date
                setupUI(date)
            }
        }
    }

    private fun onMonthClicked(dayData: DayData) {
        val bundle = bundleOf(
            DayDetailFragment.ARG_PARAM1 to dayData
        )
        findNavController().navigate(R.id.action_nav_privat_to_nav_privat_day_detail, bundle)
    }
}


