package hyeon9mak.membermanagementservice.external

import hyeon9mak.membermanagementservice.application.authentication.SmsMessageSender

class FakeSmsMessageSender : SmsMessageSender {
    override fun send(phoneNumber: String, message: String) {
        println("phoneNumber: $phoneNumber, message: $message")
    }
}
