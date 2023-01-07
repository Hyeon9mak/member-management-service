package hyeon9mak.membermanagementservice.web

import hyeon9mak.membermanagementservice.application.register.MemberRegisterRequest
import hyeon9mak.membermanagementservice.application.register.MemberRegisterResponse
import hyeon9mak.membermanagementservice.application.register.MemberRegisterService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RequestMapping("/api/v1/members")
@RestController
class MemberController(
    private val memberRegisterService: MemberRegisterService,
) {
    @PostMapping("/register")
    fun register(@Valid @RequestBody memberRegisterRequest: MemberRegisterRequest): MemberRegisterResponse =
        memberRegisterService.register(memberRegisterRequest)
}
