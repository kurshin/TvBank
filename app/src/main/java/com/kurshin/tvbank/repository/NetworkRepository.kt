package com.kurshin.tvbank.repository

import com.kurshin.tvbank.network.BinaryResult
import com.kurshin.tvbank.network.api.PrivatApi
import com.kurshin.tvbank.network.model.balance_request.Data
import com.kurshin.tvbank.network.model.balance_request.Merchant
import com.kurshin.tvbank.network.model.balance_request.Prop
import com.kurshin.tvbank.network.model.balance_request.RequestBalance
import com.kurshin.tvbank.network.model.balance_response.ResponseBalance
import com.kurshin.tvbank.network.safeCall
import org.apache.commons.codec.digest.DigestUtils
import org.simpleframework.xml.core.Persister
import java.io.ByteArrayOutputStream
import java.nio.charset.StandardCharsets
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val privatApi: PrivatApi,
    prefs: PreferencesRepository
) {

    suspend fun getCurrentBalance(): BinaryResult<ResponseBalance> = safeCall {
        privatApi.currentBalance(getBalanceRequest())
    }

    suspend fun getBalanceHistory(day1: String, day2: String): BinaryResult<ResponseBalance> = safeCall {
        privatApi.balanceHistory(getBalanceHistoryRequest(day1, day2))
    }

    private fun getBalanceRequest(): String {
        val data = Data("cmt", listOf(
            Prop("cardnum", accountInfo.cardNumber),
            Prop("country", "UA")
        ), "0", "0")
        return getDataRequest(data)
    }

    private fun getBalanceHistoryRequest(day: String, day2: String): String {
        val data = Data("cmt", listOf(
            Prop("card", accountInfo.cardNumber),
            Prop("sd", day),
            Prop("ed", day2)
        ), "0", "0")
        return getDataRequest(data)
    }

    private fun getDataRequest(data: Data): String{

        var ous = ByteArrayOutputStream()
        Persister().write(data, ous)
        val dataString = ous.toString(StandardCharsets.UTF_8.name())
            .replace("<data>", "")
            .replace("</data>", "")
            .replace("\n", "")
            .replace("   ", "")
        ous.close()

        val signature = "$dataString${accountInfo.password}"
        val codedSignature = DigestUtils.sha1Hex(DigestUtils.md5Hex(signature))
        val merchant = Merchant(accountInfo.userId, codedSignature)

        val request = RequestBalance("1.0", merchant, data)
        ous = ByteArrayOutputStream()
        Persister().write(request, ous)
        val result = ous.toString(StandardCharsets.UTF_8.name())
            .replace("\n", "")
            .replace("   ", "")
        ous.close()

        return result
    }

    private val accountInfo = prefs.accountInfo
    private val REQUEST_START =
        "<?xml version=\"1.0\" encoding=\"UTF-8\"?><request version=\"1.0\"><merchant><id>${accountInfo.userId}</id><signature>@</signature></merchant><data>"
    private val REQUEST_DATA =
        "<oper>cmt</oper><wait>0</wait><test>0</test><payment id=\"\"><prop name=\"cardnum\" value=\"${accountInfo.cardNumber}\" /><prop name=\"country\" value=\"UA\" /></payment>"
    private val REQUEST_END = "</data></request>"
}