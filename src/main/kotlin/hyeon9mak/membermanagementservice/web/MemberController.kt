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
import io.swagger.v3.oas.annotations.Operation
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
    @Operation(
        summary = "회원가입",
        description = "전화번호 인증이 완료된 상태에서 회원가입을 진행합니다."
    )
    @PostMapping("/register")
    fun register(@Valid @RequestBody request: MemberRegisterRequest): ResponseEntity<MemberRegisterResponse> {
        val response = memberRegisterService.register(request = request)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @Operation(
        summary = "회원가입 인증번호 발송",
        description = "회원가입을 위해 전화번호 인증번호 발송을 요청합니다."
    )
    @PostMapping("/register/authentication-code")
    fun generateAuthenticationCodeForRegister(
        @Valid @RequestBody request: MemberRegisterAuthenticationCodeRequest
    ): ResponseEntity<Void> {
        memberAuthenticationCodeService.generateAuthenticationCodeForRegister(request = request)
        return ResponseEntity.ok().build()
    }

    @Operation(
        summary = "비밀번호 재설정",
        description = "전화번호 인증이 완료된 상태에서 비밀번호 재설정을 진행합니다."
    )
    @PutMapping("/reset-password")
    fun resetPassword(
        @Valid @RequestBody request: MemberPasswordResetRequest
    ): ResponseEntity<Void> {
        memberPasswordResetService.resetPassword(request = request)
        return ResponseEntity.ok().build()
    }

    @Operation(
        summary = "비밀번호 재설정 인증번호 발송",
        description = "비밀번호 재설정을 위해 전화번호 인증번호 발송을 요청합니다."
    )
    @PostMapping("/reset-password/authentication-code")
    fun generateAuthenticationCodeForResetPassword(@Valid @RequestBody request: MemberPasswordResetAuthenticationCodeRequest): ResponseEntity<Void> {
        memberAuthenticationCodeService.generateAuthenticationCodeForPasswordReset(request = request)
        return ResponseEntity.ok().build()
    }

    @Operation(
        summary = "전화번호 인증",
        description = "전달 받은 전화번호 인증번호를 통해 인증을 시도합니다."
    )
    @PutMapping("/authentication")
    fun authenticate(@Valid @RequestBody request: MemberRegisterAuthenticateRequest): ResponseEntity<Void> {
        memberAuthenticationCodeService.authenticate(request = request)
        return ResponseEntity.ok().build()
    }

    @Operation(
        summary = "이메일 로그인",
        description = "이메일 + 비밀번호 조합으로 로그인을 요청합니다."
    )
    @PostMapping("/login/email")
    fun login(@Valid @RequestBody request: MemberEmailLoginRequest): ResponseEntity<MemberLoginResponse> {
        val response = memberLoginService.generateTokenByEmailLogin(request = request)
        return ResponseEntity.ok(response)
    }

    @Operation(
        summary = "닉네임 로그인",
        description = "닉네임 + 비밀번호 조합으로 로그인을 요청합니다."
    )
    @PostMapping("/login/nickname")
    fun login(@Valid @RequestBody request: MemberNicknameLoginRequest): ResponseEntity<MemberLoginResponse> {
        val response = memberLoginService.generateTokenByNicknameLogin(request = request)
        return ResponseEntity.ok(response)
    }

    @Operation(
        summary = "전화번호 로그인",
        description = "전화번호 + 비밀번호 조합으로 로그인을 요청합니다."
    )
    @PostMapping("/login/phone-number")
    fun login(@Valid @RequestBody request: MemberPhoneNumberLoginRequest): ResponseEntity<MemberLoginResponse> {
        val response = memberLoginService.generateTokenByPhoneNumberLogin(request = request)
        return ResponseEntity.ok(response)
    }

    @Operation(
        summary = "내 정보 조회",
        description = "내 개인 정보를 조회합니다. 로그인 회원만 사용 가능."
    )
    @GetMapping("/me")
    fun me(@Logined loginMember: LoginMember): ResponseEntity<MemberInfoResponse> {
        val response = memberInfoService.getInfoByEmail(email = loginMember.email)
        return ResponseEntity.ok(response)
    }
}
