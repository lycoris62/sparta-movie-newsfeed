package sparta.ifour.movietalk.domain.review.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sparta.ifour.movietalk.domain.review.entity.Review;
import sparta.ifour.movietalk.domain.review.entity.ReviewHashtag;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {
    private Long id;

    private String title;

    private String content;

    private Float ratingScore;

    private String movieName;

    private Integer countLikes;

    private List<ReviewHashtag> reviewHashtagList;

    private LocalDateTime createdAt;


    public ReviewResponseDto(Review review){
        this.id = review.getId();
        this.title = review.getTitle();
        this.content = review.getContent();
        this.ratingScore = review.getRatingScore();
        this.movieName = review.getMovieName();
        this.createdAt = review.getCreatedAt();
        this.countLikes = review.getLikeList().size();
        this.reviewHashtagList = review.getReviewHashtagList();
    }



}
