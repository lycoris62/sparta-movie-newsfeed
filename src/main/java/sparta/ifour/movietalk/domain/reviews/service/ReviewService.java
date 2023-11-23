package sparta.ifour.movietalk.domain.reviews.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.ifour.movietalk.domain.reviews.dto.request.ReviewRequestDto;
import sparta.ifour.movietalk.domain.reviews.dto.response.ReviewPreviewResponseDto;
import sparta.ifour.movietalk.domain.reviews.dto.response.ReviewResponseDto;
import sparta.ifour.movietalk.domain.reviews.entity.Hashtag;
import sparta.ifour.movietalk.domain.reviews.entity.Like;
import sparta.ifour.movietalk.domain.reviews.entity.Review;
import sparta.ifour.movietalk.domain.reviews.entity.ReviewHashtag;
import sparta.ifour.movietalk.domain.reviews.repository.HashtagRepository;
import sparta.ifour.movietalk.domain.reviews.repository.LikeRepository;
import sparta.ifour.movietalk.domain.reviews.repository.ReviewHashTagRepository;
import sparta.ifour.movietalk.domain.reviews.repository.ReviewRepository;
import sparta.ifour.movietalk.domain.user.entity.User;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewHashTagRepository reviewHashTagRepository;
    private final HashtagRepository hashtagRepository;
    private final LikeRepository likeRepository;

    @Transactional
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

    @Transactional
    public void updateReview(ReviewRequestDto requestDto, Long reviewId) { // 리뷰 수정
        Review review = getReviewById(reviewId);

        review.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getRatingScore());
    }

    @Transactional
    public void deleteReview(Long reviewId) { // 리뷰 삭제
        Review review = getReviewById(reviewId);

        reviewRepository.delete(review);
    }

    @Transactional
    public void clickLike(Long reviewId, User user) { // 좋아요 클릭시
        Optional<Like> findLike = likeRepository.findByReviewIdAndUser(reviewId, user);
        Review review = reviewRepository.findById(reviewId).get();

        if(!user.getId().equals(findLike.get().getReview().getUser().getId())) { // 내가 작성한 리뷰가 아닐 경우
            if (findLike.isPresent()) { // 이미 좋아요를 눌렀을 경우 => 삭제
                review.removeLike(findLike.get());
            } else { // 좋아요를 누르지 않은 경우 => 추가
                addLike(review, user);
            }
            reviewRepository.save(review);
        }
    }

    public void addLike(Review review, User user) {
        Like like = Like.builder()
                .user(user)
                .review(review)
                .build();

        review.addLike(like);
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

    public List<ReviewResponseDto> getReviewsBySearch(String queryname) {

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

    private Hashtag getHashtagByname(String name) {

        return hashtagRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("해당 태그를 찾을 수 없습니다."));
    }

    private Review getReviewById(Long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰글을 찾을 수 없습니다."));
    }

    private boolean doesReviewContain(String query, Review review) {
        return review.getContent().contains(query)
                || review.getTitle().contains(query)
                || review.getMovieName().contains(query);
    }


}
