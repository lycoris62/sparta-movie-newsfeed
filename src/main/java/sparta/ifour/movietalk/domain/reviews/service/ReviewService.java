package sparta.ifour.movietalk.domain.reviews.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.ifour.movietalk.domain.reviews.dto.ReviewResponseDto;
import sparta.ifour.movietalk.domain.reviews.entity.Review;
import sparta.ifour.movietalk.domain.reviews.repository.ReviewRepository;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewResponseDto getReview(Long reviewId) {

        Review review = getReviewById(reviewId);

        return new ReviewResponseDto(review);

    }




    public Review getReviewById(Long id){

        return reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰글을 찾을 수 없습니다."));

    }

}
