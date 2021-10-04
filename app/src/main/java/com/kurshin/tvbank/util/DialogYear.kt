package com.kurshin.tvbank.util

import android.app.AlertDialog
import android.content.Context
import com.kurshin.tvbank.R
import java.time.LocalDate

object DialogYear {

    private const val yearsBack = 6

    fun show(context: Context, onSelected: (LocalDate) -> Unit) {
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(context)
        dialogBuilder.setTitle(R.string.select_year)

        val years = mutableListOf<String>()
        var localDate = LocalDate.now()
        for (i in 0..yearsBack) {
            years.add((localDate.year - i).toString())
        }

        dialogBuilder.setItems(years.toTypedArray()) { dialog, which ->
            if (which > 0) {
                localDate = LocalDate.of(years[which].toInt(), 12, 31)
            }
            onSelected(localDate)
            dialog.dismiss()
        }

        dialogBuilder.show()
    }
}