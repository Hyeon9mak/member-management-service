package hyeon9mak.membermanagementservice.external

import hyeon9mak.membermanagementservice.application.authentication.SmsMessageSender
import hyeon9mak.membermanagementservice.external.sensapiclient.SENSApiClient
import hyeon9mak.membermanagementservice.external.sensapiclient.SENSSmsMessageRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

@Component
class SENSApiSmsMessageSender(
    private val sensApiClient: SENSApiClient,
    @Value("\${naver-cloud.sens.api.service-id}") private val sensApiServiceId: String,
    @Value("\${naver-cloud.sens.api.sms.message.from}") private val from: String,
) : SmsMessageSender {
    override fun send(phoneNumber: String, message: String) {
        val request = SENSSmsMessageRequest.from(
            from = from,
            to = phoneNumber,
            content = message,
        )
        sensApiClient.sendSmsMessage(serviceId = sensApiServiceId, request = request)
    }
}
