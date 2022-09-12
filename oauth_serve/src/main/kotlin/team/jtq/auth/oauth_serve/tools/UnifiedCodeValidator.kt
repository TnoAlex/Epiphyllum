package team.jtq.auth.oauth_serve.tools

import org.apache.commons.collections4.bidimap.TreeBidiMap
import javax.xml.bind.ValidationException


object UnifiedCodeValidator {
    private const val baseCode = "0123456789ABCDEFGHJKLMNPQRTUWXY"
    private val baseCodeArray = baseCode.toCharArray()
    private val wi = arrayOf(1, 3, 9, 27, 19, 26, 16, 17, 20, 29, 25, 13, 8, 24, 10, 30, 28)


    private fun generateCodes(): Map<Char, Int> {
        val codes = TreeBidiMap<Char, Int>()
        for (i in baseCode.indices) {
            codes[baseCodeArray[i]] = i
        }

        return codes
    }

    fun validateUnifiedCreditCode(unifiedCreditCode: String): Boolean {
        if ((unifiedCreditCode == "") || unifiedCreditCode.length != 18) {
            return false
        }

        val codes = generateCodes()
        var parityBit = 0
        try {
            parityBit = getParityBit(unifiedCreditCode, codes)
        } catch (e: ValidationException) {
            return false
        }

        return parityBit == codes[unifiedCreditCode.get(unifiedCreditCode.length - 1)]
    }

    private fun getParityBit(unifiedCreditCode: String, codes: Map<Char,Int>): Int {
        val businessCodeArray = unifiedCreditCode.toCharArray()

        var sum = 0
        for (i in 0 until 17) {
            val key = businessCodeArray[i]
            if (baseCode.indexOf(key) == -1) {
                throw ValidationException("第" + (i + 1) + "位传入了非法的字符" + key)
            }
            sum += (codes[key]!! * wi[i])
        }
        val result = 31 - sum % 31
        return if (result == 31) 0 else result
    }
}