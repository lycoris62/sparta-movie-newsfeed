package sparta.ifour.movietalk.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentUpdateRequestDto {

    @Size(max = 100, message = "댓글은 최대 100자까지 입력이 가능합니다.")
    @NotBlank(message = "댓글은 공백이 불가능합니다.")
    private String content;

}
