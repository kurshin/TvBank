package com.kurshin.tvbank.util

import android.app.Application
import android.text.SpannableString
import android.text.style.RelativeSizeSpan
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.gson.Gson
import com.kurshin.tvbank.BuildConfig
import com.kurshin.tvbank.R
import com.kurshin.tvbank.repository.AccountInfo
import java.io.IOException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

val FragmentManager.currentNavigationFragment: Fragment?
    get() = primaryNavigationFragment?.childFragmentManager?.fragments?.first()

val patternDate: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMM yyy")
fun LocalDate.toCurrentYearTitle() : String {
    return format(patternDate)
}

val patternYear: DateTimeFormatter = DateTimeFormatter.ofPattern("yyy")
fun LocalDate.toYearTitle() : String {
    return format(patternYear)
}

val patternDay: DateTimeFormatter = DateTimeFormatter.ofPattern("dd EEE")
fun LocalDate.toDayTitle() : String {
    return format(patternDay)
}

val patternPrivat: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyy")
fun LocalDate.toPrivatRequestStr() : String {
    return format(patternPrivat)
}

val patternTranDate = DateTimeFormatter.ofPattern("yyyy-MM-dd")
fun String.toTranDate() : Long {
    val date = LocalDate.parse(this, patternTranDate)
    return date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}

val patternTranTime = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
fun String.toTranTime(tranDate: String) : Long {
    val date = LocalDateTime.parse("${tranDate}T${this}", patternTranTime)
    return date.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()
}

val patternPrivatTitle: DateTimeFormatter = DateTimeFormatter.ofPattern("MMM yyy")
fun LocalDate.toPrivatTitle() : String {
    return format(patternPrivatTitle)
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