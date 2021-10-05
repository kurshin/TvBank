package com.kurshin.tvbank.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun ViewModel.launch(
    context: CoroutineContext = EmptyCoroutineContext,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return viewModelScope.launch(context, start, block)
}

/**
 * Executes suspending block using IO dispatcher
 */
suspend fun <T> io(
    block: suspend CoroutineScope.() -> T
): T = withContext(
    Dispatchers.IO,
    block
)

/**
 * Executes suspending block using Default dispatcher
 */
suspend fun <T> default(
    block: suspend CoroutineScope.() -> T
): T = withContext(
    Dispatchers.Default,
    block
)

/**
 * Executes suspending block using Main dispatcher
 */
suspend fun <T> main(
    block: suspend CoroutineScope.() -> T
): T = withContext(
    Dispatchers.Main,
    block
)
