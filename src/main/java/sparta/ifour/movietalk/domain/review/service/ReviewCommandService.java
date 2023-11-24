package sparta.ifour.movietalk.domain.review.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sparta.ifour.movietalk.domain.review.dto.request.ReviewRequestDto;
import sparta.ifour.movietalk.domain.review.dto.response.ReviewPreviewResponseDto;
import sparta.ifour.movietalk.domain.review.entity.Like;
import sparta.ifour.movietalk.domain.review.entity.Review;
import sparta.ifour.movietalk.domain.review.repository.LikeRepository;
import sparta.ifour.movietalk.domain.review.repository.ReviewRepository;
import sparta.ifour.movietalk.domain.user.entity.User;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewCommandService {

	private final ReviewRepository reviewRepository;
	private final LikeRepository likeRepository;

	public ReviewPreviewResponseDto createReview(ReviewRequestDto requestDto, User user) { // 리뷰 생성
		Review review = Review.builder()
			.title(requestDto.getTitle())
			.content(requestDto.getContent())
			.ratingScore(requestDto.getRatingScore())
			.movieName(requestDto.getMovieName())
			.user(user)
			.build();

		Review saveReview = reviewRepository.save(review);

		return new ReviewPreviewResponseDto(saveReview);
	}

	public void updateReview(ReviewRequestDto requestDto, Long reviewId, User user) { // 리뷰 수정
		Review review = getReviewById(reviewId);

		validateAuthor(review, user);
		review.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getRatingScore());
	}

	public void deleteReview(Long reviewId, User user) { // 리뷰 삭제
		Review review = getReviewById(reviewId);

		validateAuthor(review, user);
		reviewRepository.delete(review);
	}

	private void validateAuthor(Review review, User user) {
		if (!user.getLoginId().equals(review.getUser().getLoginId()))
			throw new AccessDeniedException("다른 사용자가 작성한 리뷰는 수정할 수 없습니다.");
	}

	public void clickLike(Long reviewId, User user) { // 좋아요 클릭시

		Review review = getReviewById(reviewId);
		validateAuthorClick(user, review); // 좋아요를 누른사람이 작성자인지 확인

		likeRepository.findByReviewIdAndUserId(reviewId, user.getId())
			.ifPresentOrElse(
				(like) -> {
					review.removeLike(like);
					likeRepository.delete(like);
				}, // 이미 좋아요 눌렀을 경우
				() -> {
					Like like = new Like(user, review);
					review.addLike(like); // 좋아요를 누르지 않은 경우
					likeRepository.save(like);
				}
			);
	}

	private void validateAuthorClick(User user, Review review) {
		if (user.getLoginId().equals(review.getUser().getLoginId())) {
			throw new AccessDeniedException("작성자는 좋아요를 누를 수 없습니다.");
		}
	}

	private Review getReviewById(Long id) {
		return reviewRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("해당 리뷰글을 찾을 수 없습니다."));
	}
}
