package hyeon9mak.membermanagementservice.application.authentication

interface SmsMessageSender {

    fun send(phoneNumber: String, message: String)
}
