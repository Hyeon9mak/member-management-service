package hyeon9mak.membermanagementservice

import hyeon9mak.membermanagementservice.external.sensapiclient.SENSApiClient
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@EnableFeignClients(clients = [SENSApiClient::class])
@SpringBootApplication
class MemberManagementServiceApplication

fun main(args: Array<String>) {
	runApplication<MemberManagementServiceApplication>(*args)
}
