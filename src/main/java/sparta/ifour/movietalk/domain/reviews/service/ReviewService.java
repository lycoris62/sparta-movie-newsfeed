package sparta.ifour.movietalk.domain.reviews.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.ifour.movietalk.domain.reviews.dto.request.ReviewRequestDto;
import sparta.ifour.movietalk.domain.reviews.dto.response.ReviewResponseDto;
import sparta.ifour.movietalk.domain.reviews.entity.Hashtag;
import sparta.ifour.movietalk.domain.reviews.entity.Review;
import sparta.ifour.movietalk.domain.reviews.entity.ReviewHashtag;
import sparta.ifour.movietalk.domain.reviews.repository.HashtagRepository;
import sparta.ifour.movietalk.domain.reviews.repository.ReviewHashTagRepository;
import sparta.ifour.movietalk.domain.reviews.repository.ReviewRepository;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewHashTagRepository reviewHashTagRepository;
    private final HashtagRepository hashtagRepository;

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

    public List<ReviewResponseDto> getReviewsByTag(String hashtagName) {
        Hashtag hashtag = getHashtagByname(hashtagName);

        return hashtag.getReviewHashtagList().stream()
                .map(ReviewHashtag::getReview)
                .map(ReviewResponseDto::new)
                .toList();
    }

    private Hashtag getHashtagByname(String name){

        return hashtagRepository.findByName(name)
                .orElseThrow(()->new IllegalArgumentException("해당 태그를 찾을 수 없습니다."));
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
