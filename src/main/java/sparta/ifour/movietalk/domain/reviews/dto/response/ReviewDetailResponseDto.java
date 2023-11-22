package sparta.ifour.movietalk.domain.reviews.dto.response;

import lombok.Getter;
import sparta.ifour.movietalk.domain.comment.dto.response.CommentCreateResponseDto;
import sparta.ifour.movietalk.domain.reviews.entity.Review;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 상세 조회 관련 ResponseDto
 */
@Getter
public class ReviewDetailResponseDto {
    private Long id;
    private String title;
    private String content;
    private Float ratingScore;
    private String movieName;
    private LocalDateTime createdAt;
    private List<CommentCreateResponseDto> commentList;

    public ReviewDetailResponseDto(Review review, List<CommentCreateResponseDto> commentList) {
        this.id = review.getId();
        this.title = review.getTitle();
        this.content = review.getContent();
        this.ratingScore = review.getRatingScore();
        this.movieName = review.getMovieName();
        this.createdAt = review.getCreatedAt();
        this.commentList = commentList;
    }
}
