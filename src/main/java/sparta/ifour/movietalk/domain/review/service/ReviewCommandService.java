package sparta.ifour.movietalk.domain.review.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.ifour.movietalk.domain.review.dto.request.ReviewRequestDto;
import sparta.ifour.movietalk.domain.review.dto.response.ReviewPreviewResponseDto;
import sparta.ifour.movietalk.domain.review.entity.Hashtag;
import sparta.ifour.movietalk.domain.review.entity.Like;
import sparta.ifour.movietalk.domain.review.entity.Review;
import sparta.ifour.movietalk.domain.review.entity.ReviewHashtag;
import sparta.ifour.movietalk.domain.review.repository.HashtagRepository;
import sparta.ifour.movietalk.domain.review.repository.LikeRepository;
import sparta.ifour.movietalk.domain.review.repository.ReviewHashTagRepository;
import sparta.ifour.movietalk.domain.review.repository.ReviewRepository;
import sparta.ifour.movietalk.domain.user.entity.User;
import sparta.ifour.movietalk.global.exception.InvalidAccessException;
import sparta.ifour.movietalk.global.exception.NotFoundException;

@Service
@Transactional
@RequiredArgsConstructor
public class ReviewCommandService {

	private final ReviewRepository reviewRepository;
	private final LikeRepository likeRepository;
	private final HashtagRepository hashtagRepository;
	private final ReviewHashTagRepository reviewHashTagRepository;

	public ReviewPreviewResponseDto createReview(ReviewRequestDto requestDto, User user) { // 리뷰 생성
		Review review = Review.builder()
			.title(requestDto.getTitle())
			.content(requestDto.getContent())
			.ratingScore(requestDto.getRatingScore())
			.movieName(requestDto.getMovieName())
			.user(user)
			.build();

		Review saveReview = reviewRepository.save(review);

		// reviewHashTagRepository.save(reviewHashtag); 이것보다 먼저 와야한다.

		List<Hashtag> HashtagList= requestDto.getTagList().stream()
			.map(Hashtag::new).toList();

		HashtagList.stream()
			.filter(tag-> !hashtagRepository.existsByName(tag.getName()))
			.forEach(hashtagRepository::save);

		HashtagList
			.forEach(tag -> {
				ReviewHashtag reviewHashtag = new ReviewHashtag(review, tag);
				reviewHashTagRepository.save(reviewHashtag);
			});



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
			throw new InvalidAccessException();
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
			throw new AccessDeniedException("작성자는 리뷰에 좋아요를 누를 수 없습니다.");
		}
	}

	private Review getReviewById(Long id) {
		return reviewRepository.findById(id)
			.orElseThrow(NotFoundException::new);
	}
}
