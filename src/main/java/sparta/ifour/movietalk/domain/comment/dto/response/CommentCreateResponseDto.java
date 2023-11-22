package sparta.ifour.movietalk.domain.comment.dto.response;

import lombok.Getter;
import sparta.ifour.movietalk.domain.comment.entity.Comment;

import java.time.LocalDateTime;

@Getter
public class CommentCreateResponseDto {

    private Long id;
    private String content;
    private LocalDateTime createAt;

    public CommentCreateResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createAt = comment.getCreatedAt();
    }
}
