package sparta.ifour.movietalk.domain.comment.dto.response;

import sparta.ifour.movietalk.domain.comment.entity.Comment;

import java.time.LocalDateTime;

public class CommentResponseDto {


    private Long id;
    private String content;
    private LocalDateTime createAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.createAt = comment.getCreatedAt();
    }

}
