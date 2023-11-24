package sparta.ifour.movietalk.domain.reviews.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.ifour.movietalk.domain.reviews.dto.request.ReviewRequestDto;
import sparta.ifour.movietalk.domain.reviews.dto.response.ReviewDetailResponseDto;
import sparta.ifour.movietalk.domain.reviews.dto.response.ReviewPreviewResponseDto;
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

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewHashTagRepository reviewHashTagRepository;
    private final HashtagRepository hashtagRepository;
    private final LikeRepository likeRepository;

    @Transactional
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

    @Transactional
    public void updateReview(ReviewRequestDto requestDto, Long reviewId, User user) { // 리뷰 수정
        Review review = getReviewById(reviewId);

        validateAuthor(review, user);
        review.update(requestDto.getTitle(), requestDto.getContent(), requestDto.getRatingScore());
    }

    @Transactional
    public void deleteReview(Long reviewId, User user) { // 리뷰 삭제
        Review review = getReviewById(reviewId);

        validateAuthor(review, user);
        reviewRepository.delete(review);
    }

    private void validateAuthor(Review review, User user) {
        if(!user.getLoginId().equals(review.getUser().getLoginId()))
            throw new AccessDeniedException("다른 사용자가 작성한 리뷰는 수정할 수 없습니다.");
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

    public ReviewDetailResponseDto getReview(Long reviewId) {

        Review review = getReviewById(reviewId);

        return new ReviewDetailResponseDto(review);

    }


    public List<ReviewPreviewResponseDto> getReviewsAll(String sort) {

        return getListAllByDate(sort).stream()
                .map(ReviewPreviewResponseDto::new)
                .toList();

    }


    public List<ReviewPreviewResponseDto> getReviewsBySearch(String sort,String query) {

        return getListAllByDate(sort).stream()
                .filter(review -> doesReviewContain(query, review))
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

    private  List<Review> getListAllByDate(String sort){
        LocalDate current = LocalDate.now();
        if(sort.equals("recent")) {

            return reviewRepository.findAllByOrderByCreatedAtDesc();
        }

        else if(sort.equals("liketoday")){

            List<Review> reviewListAllRecent = reviewRepository.findAllByCreatedAtAfterOrderByCreatedAtDesc(current);

            return reviewListAllRecent;

        }

        else if(sort.equals("likeWeek")){
            LocalDate oneWeek = current.minusWeeks(1);

            return reviewRepository.findAllByCreatedAtAfterOrderByCreatedAtDesc(oneWeek);
        }

        else if(sort.equals("likeMonth")){
            LocalDate oneMonth = current.minusMonths(1);

            return reviewRepository.findAllByCreatedAtAfterOrderByCreatedAtDesc(oneMonth);
        }
        else if(sort.equals("likeYear")){
            LocalDate oneYear = current.minusYears(1);

            return reviewRepository.findAllByCreatedAtAfterOrderByCreatedAtDesc(oneYear);
        }

        else{
            System.out.println("잘못된입력입니다."); // 나중에 예외처리 만들면 처리해줌
            return Collections.emptyList();
        }


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
