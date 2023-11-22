package sparta.ifour.movietalk.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUpdateRequestDto {

    @Size(max = 100)
    @NotBlank
    private String content;

}
