package hyeon9mak.membermanagementservice.web

import hyeon9mak.membermanagementservice.application.authentication.MemberAuthenticateRequest
import hyeon9mak.membermanagementservice.application.authentication.MemberAuthenticationCodeRequest
import hyeon9mak.membermanagementservice.application.authentication.MemberAuthenticationService
import hyeon9mak.membermanagementservice.application.authentication.MemberEmailLoginRequest
import hyeon9mak.membermanagementservice.application.authentication.MemberLoginResponse
import hyeon9mak.membermanagementservice.application.authentication.MemberNicknameLoginRequest
import hyeon9mak.membermanagementservice.application.authentication.MemberPhoneNumberLoginRequest
import hyeon9mak.membermanagementservice.application.register.MemberRegisterRequest
import hyeon9mak.membermanagementservice.application.register.MemberRegisterResponse
import hyeon9mak.membermanagementservice.application.register.MemberRegisterService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Validated
@RequestMapping("/api/v1/members")
@RestController
class MemberController(
    private val memberRegisterService: MemberRegisterService,
    private val memberAuthenticationService: MemberAuthenticationService,
) {
    @PostMapping("/register")
    fun register(@Valid @RequestBody request: MemberRegisterRequest): ResponseEntity<MemberRegisterResponse> {
        val response = memberRegisterService.register(request = request)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PostMapping("/authentication-code")
    fun generateAuthenticationCode(
        @Valid @RequestBody request: MemberAuthenticationCodeRequest
    ): ResponseEntity<Void> {
        memberAuthenticationService.generateAuthenticationCodeForRegister(request = request)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping("/authentication")
    fun authenticate(@Valid @RequestBody request: MemberAuthenticateRequest): ResponseEntity<Void> {
        memberAuthenticationService.authenticate(request = request)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/login/email")
    fun login(@Valid @RequestBody request: MemberEmailLoginRequest): ResponseEntity<MemberLoginResponse> {
        val response = memberAuthenticationService.generateTokenByEmailLogin(request = request)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/login/nickname")
    fun login(@Valid @RequestBody request: MemberNicknameLoginRequest): ResponseEntity<MemberLoginResponse> {
        val response = memberAuthenticationService.generateTokenByNicknameLogin(request = request)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/login/phone-number")
    fun login(@Valid @RequestBody request: MemberPhoneNumberLoginRequest): ResponseEntity<MemberLoginResponse> {
        val response = memberAuthenticationService.generateTokenByPhoneNumberLogin(request = request)
        return ResponseEntity.ok(response)
    }
}
