package sparta.ifour.movietalk.global.config.security.error;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private List<String> messages = new ArrayList<>();

    public ErrorResponse(String message) {
        this.messages.add(message);
    }

    public ErrorResponse(List<String> messages) {
        this.messages = messages;
    }
}
