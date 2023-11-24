package sparta.ifour.movietalk.domain.review.service;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.ifour.movietalk.domain.review.dto.response.ReviewDetailResponseDto;
import sparta.ifour.movietalk.domain.review.dto.response.ReviewPreviewResponseDto;
import sparta.ifour.movietalk.domain.review.entity.Like;
import sparta.ifour.movietalk.domain.review.entity.Review;
import sparta.ifour.movietalk.domain.review.enums.ReviewSort;
import sparta.ifour.movietalk.domain.review.repository.LikeRepository;
import sparta.ifour.movietalk.domain.review.repository.ReviewRepository;
import sparta.ifour.movietalk.global.exception.NotFoundException;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewQueryService {

	private final ReviewRepository reviewRepository;
	private final LikeRepository likeRepository;

	public ReviewDetailResponseDto getReview(Long reviewId) {

		Review review = getReviewById(reviewId);
		return new ReviewDetailResponseDto(review);
	}

	private Review getReviewById(Long id) {
		return reviewRepository.findById(id)
			.orElseThrow(() -> new NotFoundException());
	}

	public List<ReviewPreviewResponseDto> getReviews(String sort, String query, String hashtag) {

		validateQueryAndHashtag(query, hashtag);
		List<Review> reviews = getReviewsBySort(sort);

		return filtering(reviews, query, hashtag)
			.map(ReviewPreviewResponseDto::new)
			.toList();
	}

	private void validateQueryAndHashtag(String query, String hashtag) {
		if (query != null && hashtag != null) {
			throw new IllegalArgumentException("쿼리와 해시태그는 같이 받을 수 없습니다.");
		}
	}

	private List<Review> getReviewsBySort(String sort) {

		log.info("sort : {}", sort);
		ReviewSort reviewSort = ReviewSort.parse(sort);

		if (reviewSort.equals(ReviewSort.RECENT)) {
			return reviewRepository.findAllByOrderByCreatedAtDesc();
		}

		Set<Map.Entry<Review, Long>> reviewLikeCounts = likeRepository
			.findAllByCreatedAtAfter(getStartDate(reviewSort).atStartOfDay())
			.stream()
			.collect(groupingBy(Like::getReview, counting()))
			.entrySet();

		return reviewLikeCounts
			.stream()
			.sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
			.map(Map.Entry::getKey)
			.toList();
	}

	private LocalDate getStartDate(ReviewSort sort) {
		LocalDate today = LocalDate.now();

		if (sort.equals(ReviewSort.LIKE_TODAY)) {
			return today;
		}
		if (sort.equals(ReviewSort.LIKE_WEEK)) {
			return today.minusWeeks(1);
		}
		if (sort.equals(ReviewSort.LIKE_MONTH)) {
			return today.minusMonths(1);
		}
		return today.minusYears(1);
	}

	private Stream<Review> filtering(List<Review> reviews, String query, String hashtag) {
		if (query == null && hashtag == null) {
			return reviews.stream();
		}

		if (query != null) {
			return reviews.stream().filter(review -> doesReviewContain(query, review));
		}

		return reviews.stream().filter(review -> review.getReviewHashtagList().contains(hashtag));
	}

	private boolean doesReviewContain(String query, Review review) {
		return review.getContent().contains(query)
			   || review.getTitle().contains(query)
			   || review.getMovieName().contains(query);
	}
}
