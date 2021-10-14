package com.kurshin.tvbank.ui.privat24.day_detail.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.components.YAxis.AxisDependency
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.kurshin.tvbank.R
import com.kurshin.tvbank.databinding.FragmentPrivatDayDetailBinding
import com.kurshin.tvbank.db.entity.BalanceEntity
import com.kurshin.tvbank.ui.privat24.calendar.presenter.DayData
import com.kurshin.tvbank.ui.privat24.day_detail.view_model.PrivatDetailViewModel
import com.kurshin.tvbank.util.showDialog
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

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

        setupChart()
        setupObservables()
        getBalanceHistory()
    }

    private fun setupObservables() {
        viewModel.currentBalance.observe(viewLifecycleOwner, ::fillChart)
        viewModel.requestError.observe(viewLifecycleOwner, ::showDialog)
    }

    private fun getBalanceHistory() {
        if (dayData.isDay()) {
            viewModel.getBalanceHistory(dayData.day)
        } else {
            val monthEndDate = LocalDate.of(
                dayData.day.year,
                dayData.day.month,
                dayData.day.lengthOfMonth()
            )
            viewModel.getBalanceHistory(dayData.day, monthEndDate)
        }
    }

    private fun setupChart() {
        binding.lineChart.marker = MarkerView(requireContext(), R.layout.view_chart_marker)

        //Part6
        binding.lineChart.xAxis.labelRotationAngle = 0f

        //Part7
        binding.lineChart.axisRight.isEnabled = false
        binding.lineChart.getAxis(AxisDependency.LEFT).axisMinimum = 0f
        binding.lineChart.getAxis(AxisDependency.LEFT).axisMaximum = 700000f

        //Part8
        binding.lineChart.setTouchEnabled(true)
//        binding.lineChart.setPinchZoom(true)

        //Part9
//        binding.lineChart.description.text = "PrivatBank Expenses"
        binding.lineChart.setNoDataText("No expenses yet!")

        //Part10
        binding.lineChart.animateX(800, Easing.EaseInExpo)
    }

    private fun fillChart(balanceList: List<BalanceEntity>) {
        val entries = ArrayList<Entry>()

        val calendar = GregorianCalendar.getInstance()

        balanceList.forEach {
            calendar.timeInMillis = it.trantime
            entries.add(Entry(calendar.get(Calendar.DAY_OF_MONTH).toFloat(), it.rest.toFloat()).apply { data = it })
        }

        val lineDataSet = LineDataSet(entries, "Days")

        lineDataSet.setDrawValues(false)
        lineDataSet.setDrawFilled(true)
        lineDataSet.lineWidth = 3f
        lineDataSet.fillColor = Color.GRAY
        lineDataSet.fillAlpha = Color.RED

        binding.lineChart.data = LineData(lineDataSet)
    }

    companion object {
        const val ARG_PARAM1 = "dayData"
    }
}