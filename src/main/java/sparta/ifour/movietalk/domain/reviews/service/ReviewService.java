package sparta.ifour.movietalk.domain.reviews.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.ifour.movietalk.domain.reviews.dto.request.ReviewRequestDto;
import sparta.ifour.movietalk.domain.reviews.dto.response.ReviewPreviewResponseDto;
import sparta.ifour.movietalk.domain.reviews.dto.response.ReviewResponseDto;
import sparta.ifour.movietalk.domain.reviews.entity.Review;
import sparta.ifour.movietalk.domain.reviews.repository.ReviewRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewPreviewResponseDto createReview(ReviewRequestDto requestDto) { // 리뷰 생성
        Review review = Review.builder()
                .title(requestDto.getTitle())
                .content(requestDto.getContent())
                .ratingScore(requestDto.getRatingScore())
                .movieName(requestDto.getMovieName())
                .build();

        reviewRepository.save(review);

        return new ReviewPreviewResponseDto(review);
    }

    public void updateReview(ReviewRequestDto requestDto, Long reviewId) { // 리뷰 수정
        Review review = getReviewById(reviewId);

        review.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getRatingScore());
    }

    public void deleteReview(Long reviewId) { // 리뷰 삭제
        Review review = getReviewById(reviewId);

        reviewRepository.delete(review);
    }

    public ReviewResponseDto getReview(Long reviewId) {

        Review review = getReviewById(reviewId);

        return new ReviewResponseDto(review);

    }

    public List<ReviewResponseDto> getReviewsAll() {

        List<Review> reviewListAll = reviewRepository.findAll();

        return reviewListAll.stream()
                .map(ReviewResponseDto::new)
                .toList();

    }

    public List<ReviewResponseDto> getReviewsBySearch(String queryname){

        return reviewRepository.findAll()
                .stream()
                .filter(review -> doesReviewContain(queryname, review))
                .map(ReviewResponseDto::new)
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
