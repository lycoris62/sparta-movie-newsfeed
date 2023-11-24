package sparta.ifour.movietalk.domain.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreateRequestDto {

    @Size(max = 100, message = "댓글은 최대 100자까지 입력이 가능합니다.")
    @NotBlank(message = "댓글은 공백이 불가능합니다.")
    private String content;

    @NotNull(message = "reviewId는 필수 입력값입니다.")
    private Long reviewId;

}
