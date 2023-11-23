package sparta.ifour.movietalk.domain.comment.dto.response;

import lombok.Getter;
import sparta.ifour.movietalk.domain.comment.entity.Comment;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {


    private Long id;
    private String content;
    private LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
    }

}
