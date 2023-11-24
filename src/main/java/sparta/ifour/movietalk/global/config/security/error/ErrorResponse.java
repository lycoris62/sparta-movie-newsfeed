package sparta.ifour.movietalk.global.config.security.error;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private String message;

    public ErrorResponse(String message) {
        this.message = message;
    }
}
