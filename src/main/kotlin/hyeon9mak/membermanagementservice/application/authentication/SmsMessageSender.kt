package hyeon9mak.membermanagementservice.application.authentication

import org.springframework.stereotype.Component

@Component
interface SmsMessageSender {

    fun send(phoneNumber: String, message: String)
}
