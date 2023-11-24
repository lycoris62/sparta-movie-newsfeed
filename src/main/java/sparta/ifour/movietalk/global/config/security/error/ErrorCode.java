package sparta.ifour.movietalk.global.config.security.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "전달받은 id에 해당하는 리소스가 없음");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
