package sparta.ifour.movietalk.global.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "전달받은 값에 해당하는 리소스가 없음"),
    INVALID_ACCESS(HttpStatus.BAD_REQUEST, "본인의 리뷰, 댓글만 수정 및 삭제가 가능");

    private final HttpStatus httpStatus;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
