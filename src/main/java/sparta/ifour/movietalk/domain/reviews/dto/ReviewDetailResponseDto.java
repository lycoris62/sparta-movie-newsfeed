package sparta.ifour.movietalk.domain.reviews.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.ifour.movietalk.domain.reviews.entity.Review;

import java.time.LocalDateTime;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDetailResponseDto {

    private Long id;

    private String title;

    private String content;

    private Double ratingScore;

    private String movieName;

    private LocalDateTime createdAt;


    public ReviewDetailResponseDto(Review review){
        this.id = review.getId();
        this.title = review.getTitle();
        this.content = review.getContent();
        this.ratingScore = review.getRatingScore();
        this.movieName = review.getMovieName();
        this.createdAt = review.getCreatedAt();
    }


}