package sparta.ifour.movietalk.domain.comment.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateRequestDto {

    @Max(100)
    @NotBlank
    private String content;

}
