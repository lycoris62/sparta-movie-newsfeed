package sparta.ifour.movietalk.domain.reviews.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.ifour.movietalk.domain.reviews.dto.ReviewRequestDto;
import sparta.ifour.movietalk.domain.reviews.dto.ReviewResponseDto;
import sparta.ifour.movietalk.domain.reviews.entity.Review;
import sparta.ifour.movietalk.domain.reviews.repository.ReviewRepository;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewResponseDto createReview(ReviewRequestDto requestDto) {
        Review review = Review.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .ratingScore(requestDto.getRatingScore())
                .movieName(requestDto.getMovieName())
                .build();

        reviewRepository.save(review);

        return new ReviewResponseDto(review);
    }

    public ReviewResponseDto getReview(Long reviewId) {

        Review review = getReviewById(reviewId);

        return new ReviewResponseDto(review);

    }

    public List<ReviewResponseDto> getReviewsAll() {

        List<Review> reviewListAll = reviewRepository.findAll();

        if(reviewListAll.isEmpty()){
            return Collections.emptyList();
        }

        return reviewListAll.stream()
                .map(ReviewResponseDto::new)
                .toList();

    }

    private Review getReviewById(Long id){
        return reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰글을 찾을 수 없습니다."));
    }
}
