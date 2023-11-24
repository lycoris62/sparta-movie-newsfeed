package sparta.ifour.movietalk.global.config.security.error;

import static sparta.ifour.movietalk.global.config.security.error.ErrorCode.NOT_FOUND;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sparta.ifour.movietalk.global.config.security.exception.NotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        ErrorResponse response = new ErrorResponse(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException e) {
        ErrorResponse response = new ErrorResponse(NOT_FOUND.getMessage());
        return new ResponseEntity<>(response, NOT_FOUND.getHttpStatus());
    }

}
