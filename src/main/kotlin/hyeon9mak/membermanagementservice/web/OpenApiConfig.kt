package hyeon9mak.membermanagementservice.web

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenApiConfig {

    @Bean
    fun openApi(): OpenAPI = OpenAPI()
        .info(
            Info()
                .title("회원 관리 서비스 API")
                .description("회원 관리 서비스 API 문서")
                .version("1.0")
                .contact(
                    Contact()
                        .name("최현구")
                        .email("jinha3507@gmail.com")
                        .url("https://hyeon9mak.github.io")
                )
        )
}
