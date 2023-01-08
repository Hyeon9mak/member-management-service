package hyeon9mak.membermanagementservice.web

import hyeon9mak.membermanagementservice.application.authentication.MemberAuthenticationCodeService
import hyeon9mak.membermanagementservice.application.authentication.MemberPasswordResetAuthenticationCodeRequest
import hyeon9mak.membermanagementservice.application.authentication.MemberPasswordResetRequest
import hyeon9mak.membermanagementservice.application.authentication.MemberPasswordResetService
import hyeon9mak.membermanagementservice.application.authentication.MemberRegisterAuthenticateRequest
import hyeon9mak.membermanagementservice.application.authentication.MemberRegisterAuthenticationCodeRequest
import hyeon9mak.membermanagementservice.application.login.LoginMember
import hyeon9mak.membermanagementservice.application.login.Logined
import hyeon9mak.membermanagementservice.application.login.MemberEmailLoginRequest
import hyeon9mak.membermanagementservice.application.login.MemberLoginResponse
import hyeon9mak.membermanagementservice.application.login.MemberLoginService
import hyeon9mak.membermanagementservice.application.login.MemberNicknameLoginRequest
import hyeon9mak.membermanagementservice.application.login.MemberPhoneNumberLoginRequest
import hyeon9mak.membermanagementservice.application.read.MemberInfoResponse
import hyeon9mak.membermanagementservice.application.read.MemberInfoService
import hyeon9mak.membermanagementservice.application.register.MemberRegisterRequest
import hyeon9mak.membermanagementservice.application.register.MemberRegisterResponse
import hyeon9mak.membermanagementservice.application.register.MemberRegisterService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@Tag(name = "회원 관련 API")
@RequestMapping("/api/v1/members")
@RestController
class MemberController(
    private val memberRegisterService: MemberRegisterService,
    private val memberPasswordResetService: MemberPasswordResetService,
    private val memberLoginService: MemberLoginService,
    private val memberAuthenticationCodeService: MemberAuthenticationCodeService,
    private val memberInfoService: MemberInfoService,
) {
    @PostMapping("/register")
    fun register(@Valid @RequestBody request: MemberRegisterRequest): ResponseEntity<MemberRegisterResponse> {
        val response = memberRegisterService.register(request = request)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PostMapping("/register/authentication-code")
    fun generateAuthenticationCodeForRegister(
        @Valid @RequestBody request: MemberRegisterAuthenticationCodeRequest
    ): ResponseEntity<Void> {
        memberAuthenticationCodeService.generateAuthenticationCodeForRegister(request = request)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/reset-password")
    fun resetPassword(
        @Valid @RequestBody request: MemberPasswordResetRequest
    ): ResponseEntity<Void> {
        memberPasswordResetService.resetPassword(request = request)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/reset-password/authentication-code")
    fun generateAuthenticationCodeForResetPassword(@Valid @RequestBody request: MemberPasswordResetAuthenticationCodeRequest): ResponseEntity<Void> {
        memberAuthenticationCodeService.generateAuthenticationCodeForPasswordReset(request = request)
        return ResponseEntity.ok().build()
    }

    @PutMapping("/authentication")
    fun authenticate(@Valid @RequestBody request: MemberRegisterAuthenticateRequest): ResponseEntity<Void> {
        memberAuthenticationCodeService.authenticate(request = request)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/login/email")
    fun login(@Valid @RequestBody request: MemberEmailLoginRequest): ResponseEntity<MemberLoginResponse> {
        val response = memberLoginService.generateTokenByEmailLogin(request = request)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/login/nickname")
    fun login(@Valid @RequestBody request: MemberNicknameLoginRequest): ResponseEntity<MemberLoginResponse> {
        val response = memberLoginService.generateTokenByNicknameLogin(request = request)
        return ResponseEntity.ok(response)
    }

    @PostMapping("/login/phone-number")
    fun login(@Valid @RequestBody request: MemberPhoneNumberLoginRequest): ResponseEntity<MemberLoginResponse> {
        val response = memberLoginService.generateTokenByPhoneNumberLogin(request = request)
        return ResponseEntity.ok(response)
    }

    @GetMapping("/me")
    fun me(@Logined loginMember: LoginMember): ResponseEntity<MemberInfoResponse> {
        val response = memberInfoService.getInfoByEmail(email = loginMember.email)
        return ResponseEntity.ok(response)
    }
}
