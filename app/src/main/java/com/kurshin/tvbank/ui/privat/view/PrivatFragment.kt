package com.kurshin.tvbank.ui.privat.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.kurshin.tvbank.R
import com.kurshin.tvbank.ui.privat.view_model.PrivatViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PrivatFragment : Fragment() {

    private val viewModel: PrivatViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_privat, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSomeData()
    }
}