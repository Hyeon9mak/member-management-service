package hyeon9mak.membermanagementservice.external.sensapiclient

import feign.Response
import feign.codec.ErrorDecoder

class SENSApiClientErrorDecoder : ErrorDecoder {

    override fun decode(methodKey: String, response: Response): Exception {
        when (response.status()) {
            400 -> throw IllegalArgumentException("SENS API 요청이 잘못되었습니다.")
            401 -> throw IllegalArgumentException("SENS API 인증 정보가 올바르지 않습니다.")
            403 -> throw IllegalArgumentException("SENS API 요청이 제한되었습니다.")
            404 -> throw IllegalArgumentException("SENS API 경로를 찾을 수 없습니다.")
            429 -> throw IllegalArgumentException("SENS API 요청이 너무 많습니다.")
            500 -> throw IllegalStateException("SENS API 서버에 오류가 발생했습니다.")
            else -> throw IllegalStateException("알 수 없는 오류로 SENS API 요청이 실패했습니다.")
        }
    }
}
