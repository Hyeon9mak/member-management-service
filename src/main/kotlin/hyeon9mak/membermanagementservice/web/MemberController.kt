package hyeon9mak.membermanagementservice.web

import hyeon9mak.membermanagementservice.application.authentication.MemberAuthenticationCodeResponse
import hyeon9mak.membermanagementservice.application.authentication.MemberAuthenticationService
import hyeon9mak.membermanagementservice.application.register.MemberRegisterRequest
import hyeon9mak.membermanagementservice.application.register.MemberRegisterResponse
import hyeon9mak.membermanagementservice.application.register.MemberRegisterService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Size

@Validated
@RequestMapping("/api/v1/members")
@RestController
class MemberController(
    private val memberRegisterService: MemberRegisterService,
    private val memberAuthenticationService: MemberAuthenticationService,
) {
    @PostMapping("/register")
    fun register(@Valid @RequestBody memberRegisterRequest: MemberRegisterRequest): ResponseEntity<MemberRegisterResponse> {
        val response = memberRegisterService.register(memberRegisterRequest)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PostMapping("/authentication-code")
    fun generateAuthenticationCode(
        @Size(min = 10, max = 11, message = "전화번호는 특수문자 없이 10자 이상 11자 이하로 입력해주세요.")
        @RequestParam(name = "phone-number", required = true) phoneNumber: String,
    ): ResponseEntity<MemberAuthenticationCodeResponse> {
        val response = memberAuthenticationService.generateAuthenticationCodeForRegister(phoneNumber)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }
}
