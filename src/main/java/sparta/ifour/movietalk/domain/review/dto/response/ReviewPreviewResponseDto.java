package sparta.ifour.movietalk.domain.review.dto.response;

import lombok.Getter;
import sparta.ifour.movietalk.domain.review.entity.Hashtag;
import sparta.ifour.movietalk.domain.review.entity.Review;
import sparta.ifour.movietalk.domain.review.entity.ReviewHashtag;

import java.time.LocalDateTime;
import java.util.List;

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
    private Integer countLikes;
    private List<String> hashtagNameList;
    private LocalDateTime createdAt;



    public ReviewPreviewResponseDto(Review review){
        this.id = review.getId();
        this.title = review.getTitle();
        this.content = review.getContent();
        this.ratingScore = review.getRatingScore();
        this.movieName = review.getMovieName();
        this.createdAt = review.getCreatedAt();
        this.hashtagNameList = review.getReviewHashtagList().stream()
                .map(reviewHashtag -> reviewHashtag.getHashtag().getName())
                .toList();
        this.countLikes = review.getLikeList().size();
    }
}
