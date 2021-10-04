package com.kurshin.tvbank.ui.privat.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.leanback.app.BrowseSupportFragment
import com.kurshin.tvbank.ui.privat.view_model.PrivatViewModel
import dagger.hilt.android.AndroidEntryPoint

import androidx.leanback.widget.*
import com.kurshin.tvbank.ui.privat.builder.AdapterBuilder

@AndroidEntryPoint
class PrivatFragment : BrowseSupportFragment() {

    private val viewModel: PrivatViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AdapterBuilder.buildMonthAdapter()

        onItemViewClickedListener = OnItemViewClickedListener { _, item, _, row ->
            onMonthClicked(row.id, item)
        }
    }

    fun onBackPressed(): Boolean {
        if (!isShowingHeaders) {
            startHeadersTransition(true)
            return true
        }

        return false
    }

    private fun onMonthClicked(monthId: Long, dayData: Any) {

    }
}


