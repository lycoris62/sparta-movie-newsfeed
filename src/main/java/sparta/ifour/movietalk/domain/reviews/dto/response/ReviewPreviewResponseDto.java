package sparta.ifour.movietalk.domain.reviews.dto.response;

import lombok.Getter;
import sparta.ifour.movietalk.domain.reviews.entity.Review;

import java.time.LocalDateTime;

/**
 * 목록 조회 관련 ResponseDto
 */

@Getter
public class ReviewPreviewResponseDto {
    private Long id;
    private String title;
    private String content;
    private Float ratingScore;
    private String movieName;
    private LocalDateTime createdAt;

    public ReviewPreviewResponseDto(Review review){
        this.id = review.getId();
        this.title = review.getTitle();
        this.content = review.getContent();
        this.ratingScore = review.getRatingScore();
        this.movieName = review.getMovieName();
        this.createdAt = review.getCreatedAt();
    }
}
