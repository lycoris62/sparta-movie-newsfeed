package sparta.ifour.movietalk.domain.reviews.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sparta.ifour.movietalk.domain.reviews.dto.ReviewResponseDto;
import sparta.ifour.movietalk.domain.reviews.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewGetController {

    private final ReviewService reviewService;

    @GetMapping("/reviews") //리뷰 전체 목록 조회
    public List<ReviewResponseDto> getAllReviews(){
        List<ReviewResponseDto> reviewListAll = reviewService.getReviewsAll();
        return reviewListAll;
    }

    @GetMapping("/reviews/{reviewId}") // 특정 리뷰 상세조회
    public ReviewResponseDto getReview(@PathVariable Long reviewId){
        ReviewResponseDto reviewResponseDto = reviewService.getReview(reviewId);
        return reviewResponseDto;
    }

    @GetMapping("/reviews/query/{queryName}") //리뷰 검색 목록 조회
    public List<ReviewResponseDto> getReviewsBySearch(@PathVariable String queryName){

        return null;
    }

    @GetMapping("reviews/hashtag/{hashtagName}") // 특정 해시태그가 포함된 리뷰 조회
    public List<ReviewResponseDto> getReviewsByHashTag(@PathVariable String hashtagName){
        return List.of();

    }


}
