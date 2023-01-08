package hyeon9mak.membermanagementservice.application.authentication

import hyeon9mak.membermanagementservice.application.read.MemberReadService
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.naming.AuthenticationException

@Component
class LoginMemberResolver(
    private val jwtTokenProvider: JwtTokenProvider,
    private val memberReadService: MemberReadService,
) : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean =
        parameter.hasParameterAnnotation(Logined::class.java)

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): LoginMember {
        val authorization = webRequest.getAuthorizationFromHeader()
        val splitAuthorization = authorization.split(BLANK)
        val (tokenType, token) = splitAuthorization[0] to splitAuthorization[1]
        validateTokenType(tokenType)
        val email = jwtTokenProvider.parseToken(token = token)
        validateExistsMember(email)
        return LoginMember(email = email)
    }

    private fun NativeWebRequest.getAuthorizationFromHeader(): String =
        this.getHeader(AUTHORIZATION) ?: throw AuthenticationException("인증 정보가 존재하지 않습니다.")

    private fun validateTokenType(tokenType: String) {
        if (tokenType != BEARER) {
            throw AuthenticationException("올바른 인증 정보가 아닙니다.")
        }
    }

    private fun validateExistsMember(email: String) {
        if (memberReadService.existsMemberByEmail(email).not()) {
            throw AuthenticationException("존재하지 않는 회원입니다.")
        }
    }

    companion object {
        private const val AUTHORIZATION = "Authorization"
        private const val BEARER = "Bearer"
        private const val BLANK = " "
    }
}
