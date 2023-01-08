package hyeon9mak.membermanagementservice.external.sensapiclient

import feign.RequestInterceptor
import feign.RequestTemplate
import feign.codec.ErrorDecoder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

@Configuration
class SENSApiClientConfiguration {

    @Bean
    fun errorDecoder(): ErrorDecoder = SENSApiClientErrorDecoder()

    @Bean
    fun requestInterceptor(
        @Value("\${naver-cloud.api.access-key}") accessKey: String,
        @Value("\${naver-cloud.api.secret-key}") secretKey: String,
    ): RequestInterceptor = RequestInterceptor { requestTemplate: RequestTemplate ->
        val timestamp = System.currentTimeMillis() // current timestamp (epoch)
        val signature = makeSignature(
            method = requestTemplate.method(),
            url = requestTemplate.url(),
            accessKey = accessKey,
            secretKey = secretKey,
            timestamp = timestamp,
        )
        requestTemplate.header("x-ncp-apigw-timestamp", timestamp.toString())
        requestTemplate.header("x-ncp-iam-access-key", accessKey)
        requestTemplate.header("x-ncp-apigw-signature-v2", signature)
    }

    /**
     * https://api.ncloud-docs.com/docs/common-ncpapi
     */
    fun makeSignature(
        method: String,
        url: String,
        accessKey: String,
        secretKey: String,
        timestamp: Long,
    ): String {
        val message = "$method $url\n$timestamp\n$accessKey"

        val signingKey = SecretKeySpec(secretKey.toByteArray(), "HmacSHA256")
        val mac: Mac = Mac.getInstance("HmacSHA256")
        mac.init(signingKey)
        val rawHmac: ByteArray = mac.doFinal(message.toByteArray())
        return Base64.getEncoder().encodeToString(rawHmac)
    }

}
