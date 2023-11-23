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

import org.springframework.security.access.AccessDeniedException;
import java.util.List;

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
        Review review = getReviewById(reviewId);
        validateAuthorClick(user, review); // 좋아요를 누른사람이 작성자인지 확인

        likeRepository.findByReviewIdAndUserId(reviewId, user.getId())
                        .ifPresentOrElse(
                                (like) -> review.removeLike(like), // 이미 좋아요 눌렀을 경우
                                () -> review.addLike(new Like(user, review)) // 좋아요를 누르지 않은 경우
                        );
    }

    private void validateAuthorClick(User user, Review review) {
        if(user.getLoginId().equals(review.getUser().getLoginId())) {
            throw new AccessDeniedException("작성자는 좋아요를 누를 수 없습니다.");
        }
    }

    public ReviewResponseDto getReview(Long reviewId) {

        Review review = getReviewById(reviewId);

        return new ReviewResponseDto(review);

    }

    public List<ReviewPreviewResponseDto> getReviewsAll() {

        List<Review> reviewListAll = reviewRepository.findAll();

        return reviewListAll.stream()
                .map(ReviewPreviewResponseDto::new)
                .toList();

    }

    public List<ReviewPreviewResponseDto> getReviewsBySearch(String queryname) {

        return reviewRepository.findAll()
                .stream()
                .filter(review -> doesReviewContain(queryname, review))
                .map(ReviewPreviewResponseDto::new)
                .toList();

    }

    public List<ReviewPreviewResponseDto> getReviewsByTag(String hashtagName) {
        Hashtag hashtag = getHashtagByname(hashtagName);

        return hashtag.getReviewHashtagList().stream()
                .map(ReviewHashtag::getReview)
                .map(ReviewPreviewResponseDto::new)
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
