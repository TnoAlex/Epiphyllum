package team.jtq.auth.oauth_serve

import org.junit.jupiter.api.Test
import team.jtq.auth.oauth_serve.tools.UnifiedCodeValidator


class UnifedCodeTest {
    @Test
    fun test(){
        val value = "12100000466001597C"
        println( UnifiedCodeValidator.validateUnifiedCreditCode(value))
    }
}