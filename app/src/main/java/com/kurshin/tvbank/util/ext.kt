package com.kurshin.tvbank.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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