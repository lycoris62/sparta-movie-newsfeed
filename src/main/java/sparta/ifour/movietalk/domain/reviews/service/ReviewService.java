package sparta.ifour.movietalk.domain.reviews.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.ifour.movietalk.domain.reviews.dto.ReviewDetailResponseDto;
import sparta.ifour.movietalk.domain.reviews.dto.ReviewPreviewResponseDto;
import sparta.ifour.movietalk.domain.reviews.entity.Review;
import sparta.ifour.movietalk.domain.reviews.repository.ReviewRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewDetailResponseDto getReview(Long reviewId) {

        Review review = getReviewById(reviewId);

        return new ReviewDetailResponseDto(review);

    }

    public List<ReviewPreviewResponseDto> getReviewsAll() {

        List<Review> reviewListAll = reviewRepository.findAll();

        return reviewListAll.stream()
                .map(ReviewPreviewResponseDto::new)
                .toList();

    }

    public List<ReviewPreviewResponseDto> getReviewsBySearch(String queryname){

        return reviewRepository.findAll()
                .stream()
                .filter(review -> doesReviewContain(queryname, review))
                .map(ReviewPreviewResponseDto::new)
                .toList();

    }

    private Review getReviewById(Long id){
        return reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰글을 찾을 수 없습니다."));
    }

    private boolean doesReviewContain(String query, Review review) {
        return review.getContent().contains(query)
                || review.getTitle().contains(query)
                || review.getMovieName().contains(query);
    }

}
