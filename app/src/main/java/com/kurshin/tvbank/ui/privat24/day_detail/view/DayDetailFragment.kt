package com.kurshin.tvbank.ui.privat24.day_detail.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.kurshin.tvbank.R
import com.kurshin.tvbank.databinding.FragmentPrivatDayDetailBinding
import com.kurshin.tvbank.ui.privat24.calendar.presenter.DayData
import com.kurshin.tvbank.ui.privat24.day_detail.view_model.PrivatDetailViewModel
import com.kurshin.tvbank.util.showDialog
import com.kurshin.tvbank.util.toPrivatStr
import com.kurshin.tvbank.util.toPrivatTitle
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate

@AndroidEntryPoint
class DayDetailFragment: Fragment() {

    private val viewModel: PrivatDetailViewModel by viewModels()
    private val binding: FragmentPrivatDayDetailBinding by viewBinding()

    private  lateinit var dayData: DayData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            dayData = it.getSerializable(ARG_PARAM1) as DayData
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_privat_day_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservables()
        if (dayData.isDay()) {
            val dateStr = dayData.day.toPrivatStr()
            viewModel.getBalanceHistory(dateStr)
        } else {
            val monthStartDate = dayData.day.toPrivatStr()
            val monthEndDate = LocalDate.of(
                dayData.day.year,
                dayData.day.month,
                dayData.day.lengthOfMonth()
            ).toPrivatStr()
            viewModel.getBalanceHistory(monthStartDate, monthEndDate)
        }
    }

    private fun setupObservables() {
        viewModel.currentBalance.observe(viewLifecycleOwner) {
            var cashAmount = 0.0
            var text = ""

            it.info.statements?.statement?.forEach { transact ->

                text += "${transact.amount} - ${transact.description}\n"

                if (!transact.description.contains("51**31")) {
                    cashAmount += transact.cardamount.replace(" UAH", "").toDouble()
                }
            }

            binding.testInfo.text = text
            binding.testTitle.text = "${dayData.day.toPrivatTitle()}   $cashAmount UAH"
        }

        viewModel.requestError.observe(viewLifecycleOwner) {
            showDialog(it)
        }
    }

    companion object {
        const val ARG_PARAM1 = "dayData"
    }
}