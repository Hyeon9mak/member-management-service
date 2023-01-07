package hyeon9mak.membermanagementservice.external.sensapiclient

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

/**
 * Naver Cloud Platform - SENS
 * https://api.ncloud-docs.com/docs/ai-application-service-sens-smsv2
 */
@FeignClient(
    name = "SENSClient",
    url = "https://sens.apigw.ntruss.com",
    configuration = [SENSApiClientConfiguration::class],
)
interface SENSApiClient {

    @PostMapping("/sms/v2/services/{serviceId}/messages")
    fun sendSmsMessage(
        @PathVariable serviceId: String,
        @RequestBody request: SENSSmsMessageRequest,
    )
}
