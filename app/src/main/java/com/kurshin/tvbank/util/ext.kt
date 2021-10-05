package com.kurshin.tvbank.util

import android.app.Application
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.kurshin.tvbank.BuildConfig
import com.kurshin.tvbank.R
import com.kurshin.tvbank.repository.AccountInfo
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter

val FragmentManager.currentNavigationFragment: Fragment?
    get() = primaryNavigationFragment?.childFragmentManager?.fragments?.first()

fun LocalDate.toDateStr() : String {
    val pattern = DateTimeFormatter.ofPattern("dd MMM yyy")
    return format(pattern)
}

fun LocalDate.toYearStr() : String {
    val pattern = DateTimeFormatter.ofPattern("yyy")
    return format(pattern)
}

fun LocalDate.toDayStr() : String {
    val pattern = DateTimeFormatter.ofPattern("dd EEE")
    return format(pattern)
}

val isDebug: Boolean
    get() = BuildConfig.DEBUG && BuildConfig.BUILD_TYPE == "debug"

fun Application.getAccountInfoFromAsset(): AccountInfo? {
    val jsonString: String
    return try {
        jsonString = assets.open("bezkoder.json").bufferedReader().use { it.readText() }
        Gson().fromJson(jsonString, AccountInfo::class.java)
    } catch (ioException: IOException) {
        ioException.printStackTrace()
        null
    }
}

fun Fragment.showDialog(message: String, callback: () -> Unit = {})  {
    val alertDialogBuilder: AlertDialog.Builder = AlertDialog.Builder(requireActivity(), R.style.Theme_AppCompat_Dialog_Alert)
    alertDialogBuilder.setMessage(message)
    alertDialogBuilder.setPositiveButton(R.string.ok) { dialog, _ ->
        dialog.dismiss()
        callback()
    }
    alertDialogBuilder.create().show()
}

fun String.toSpanTitle(balance: String): SpannableString {
    val spanTitle = SpannableString("$this $balance")
    spanTitle.setSpan(
        RelativeSizeSpan(0.5f),
        length + 1,
        spanTitle.length,
        0
    )
    return spanTitle
}