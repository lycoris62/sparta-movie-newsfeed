package sparta.ifour.movietalk.domain.reviews.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.ifour.movietalk.domain.reviews.dto.response.ReviewDetailResponseDto;
import sparta.ifour.movietalk.domain.reviews.dto.response.ReviewPreviewResponseDto;
import sparta.ifour.movietalk.domain.reviews.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewQueryController {

    private final ReviewService reviewService;

    @GetMapping("/reviews") //리뷰 전체 목록 조회
    public ResponseEntity<List<ReviewPreviewResponseDto>> getAllReviews(
            @RequestParam(name = "sort") String sort) {
        List<ReviewPreviewResponseDto> reviewListAll = reviewService.getReviewsAll(sort);
        return ResponseEntity.ok(reviewListAll);
    }

    @GetMapping("/reviews/{reviewId}") // 특정 리뷰 상세조회
    public ResponseEntity<ReviewDetailResponseDto> getReview(@PathVariable Long reviewId){
        ReviewDetailResponseDto reviewResponseDto = reviewService.getReview(reviewId);
        return ResponseEntity.ok(reviewResponseDto);
    }

    @GetMapping("/reviews/query/{queryName}") //리뷰 검색 목록 조회
    public ResponseEntity<List<ReviewPreviewResponseDto>> getReviewsBySearch(@PathVariable String queryName){

        List<ReviewPreviewResponseDto> reviewListBySearch = reviewService.getReviewsBySearch(queryName);

        return ResponseEntity.ok(reviewListBySearch);
    }

    @GetMapping("reviews/hashtag/{hashtagName}") // 특정 해시태그가 포함된 리뷰 조회
    public ResponseEntity<List<ReviewPreviewResponseDto>> getReviewsByHashTag(@PathVariable String hashtagName){

        List<ReviewPreviewResponseDto> reviewListByTag = reviewService.getReviewsByTag(hashtagName);

        return ResponseEntity.ok(reviewListByTag);

    }


}
