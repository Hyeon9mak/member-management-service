package hyeon9mak.membermanagementservice.web

import hyeon9mak.membermanagementservice.exception.MemberAuthenticationException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class MemberAdvice {

    private val logger = LoggerFactory.getLogger(MemberAdvice::class.java)

    @ExceptionHandler(IllegalArgumentException::class, IllegalStateException::class)
    fun handleBadRequestException(exception: RuntimeException): ResponseEntity<ExceptionResponse> {
        logger.warn("[WARN] Bad Request Exception: {}", exception.message)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ExceptionResponse.from(message = exception.message))
    }

    @ExceptionHandler(MemberAuthenticationException::class)
    fun handleAuthenticationException(exception: MemberAuthenticationException): ResponseEntity<ExceptionResponse> {
        logger.warn("[WARN] Member Authentication Exception: {}", exception.message)
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
            .body(ExceptionResponse.from(message = exception.message))
    }

    @ExceptionHandler(InternalServerException::class)
    fun handleInternalServerException(exception: InternalServerException): ResponseEntity<ExceptionResponse> {
        logger.error("[ERROR] Internal Server Exception: {}", exception.message)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ExceptionResponse.from(message = exception.message))
    }

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception): ResponseEntity<ExceptionResponse> {
        logger.error("[ERROR] Exception: {}", exception.message)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionResponse.from(message = exception.message))
    }
}
