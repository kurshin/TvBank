package com.kurshin.tvbank.network

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

sealed class BinaryResult<out T : Any> {
    class Success<T: Any>(val data: T) : BinaryResult<T>()
    class Error(val error: Throwable?) : BinaryResult<Nothing>() {
        constructor(message: String?) : this(Throwable(message))

        val message: String?
            get() = error?.message
    }

    override fun toString(): String {
        return when (this) {
            is Success -> this.data.toString()
            is Error -> this.error?.message.toString()
        }
    }
}

suspend fun <T: Any> safeCall(action: suspend () -> T): BinaryResult<T> =
    try {
        withContext(Dispatchers.IO) {
            BinaryResult.Success(action())
        }
    } catch (t: Throwable) {
        Log.i("1111", "error in network", t)
        BinaryResult.Error(t)
    }

/**
 * This method uses @param message as an argument for BinaryResult.Error()
 */
suspend fun <T: Any> safeCall(message: String, action: suspend () -> T): BinaryResult<T> =
    try {
        withContext(Dispatchers.IO) {
            BinaryResult.Success(action())
        }
    } catch (t: Throwable) {
        Log.i("1111", "error in network1", t)
        BinaryResult.Error(Exception(message))
    }