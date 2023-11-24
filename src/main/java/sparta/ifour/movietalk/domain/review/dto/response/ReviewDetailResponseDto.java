package sparta.ifour.movietalk.domain.review.dto.response;

import lombok.Getter;
import sparta.ifour.movietalk.domain.comment.dto.response.CommentResponseDto;
import sparta.ifour.movietalk.domain.review.entity.Review;
import sparta.ifour.movietalk.domain.review.entity.ReviewHashtag;

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
    private Integer countLikes;
    private List<ReviewHashtag> reviewHashtagList;
    private LocalDateTime createdAt;
    private List<CommentResponseDto> commentList;

    public ReviewDetailResponseDto(Review review) {
        this.id = review.getId();
        this.title = review.getTitle();
        this.content = review.getContent();
        this.ratingScore = review.getRatingScore();
        this.movieName = review.getMovieName();
        this.createdAt = review.getCreatedAt();
        this.countLikes = review.getLikeList().size();
        this.reviewHashtagList = review.getReviewHashtagList();
        this.commentList = review.getCommentList().stream()
                .map(CommentResponseDto::new)
                .toList();

    }
}