package team.jtq.auth.oauth_serve.tools

import com.baomidou.mybatisplus.extension.kotlin.KtQueryWrapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.util.Base64Utils
import sun.misc.BASE64Encoder
import team.jtq.auth.oauth_serve.entity.OauthClientDetails
import team.jtq.auth.oauth_serve.service.OauthClientDetailsService
import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.Provider
import java.security.SecureRandom
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher

@SpringBootTest
class restest {

    @Autowired
    private lateinit var serice: OauthClientDetailsService
    @Test
    fun getKey() {

        val secureRandom = SecureRandom()

        /** 为RSA算法创建一个KeyPairGenerator对象 */
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        /** 利用上面的随机数据源初始化这个KeyPairGenerator对象 */
        keyPairGenerator.initialize(1024, secureRandom);
        //keyPairGenerator.initialize(KEYSIZE);

        /** 生成密匙对 */
        val keyPair = keyPairGenerator.generateKeyPair()

        /** 得到公钥 */
        val publicKey = keyPair.getPublic()

        /** 得到私钥 */
        val privateKey = keyPair.getPrivate()
//        val pkcs8KeySpec = PKCS8EncodedKeySpec(privateKey.encoded)
//        val  x509KeySpec = X509EncodedKeySpec(publicKey.encoded)
//        val keyFactory = KeyFactory.getInstance("RSA")
//        val pk = keyFactory.generatePublic(x509KeySpec)
//        val prk = keyFactory.generatePrivate(pkcs8KeySpec)
//        val prc=Cipher.getInstance(keyFactory.algorithm)
//        val pc = Cipher.getInstance(keyFactory.algorithm)
//        pc.init(Cipher.ENCRYPT_MODE, pk)
//        prc.init(Cipher.DECRYPT_MODE,prk)
//        val s =pc.doFinal("123".toByteArray())
//        println(String(s))
//        println(String(prc.doFinal(s)))

//
        val publicKeyBytes = publicKey.encoded
        val privateKeyBytes = privateKey.encoded

        val publicKeyBase64 =  Base64Utils.encodeToString(publicKeyBytes)
        val privateKeyBase64 =  Base64Utils.encodeToString(privateKeyBytes)
        val query = KtUpdateWrapper(OauthClientDetails::class.java)
        query.eq(OauthClientDetails::appKey,"1293380307393789953").set(OauthClientDetails::appPublicKey,publicKeyBase64)
        serice.baseMapper.update(null,query)
        println("publicKeyBase64:");
        println(publicKeyBase64)
        println()
        println("privateKeyBase64:" );
        println()
        println(privateKeyBase64)

    }

}