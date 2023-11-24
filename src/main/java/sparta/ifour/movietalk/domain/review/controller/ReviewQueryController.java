package sparta.ifour.movietalk.domain.review.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sparta.ifour.movietalk.domain.review.dto.response.ReviewDetailResponseDto;
import sparta.ifour.movietalk.domain.review.dto.response.ReviewPreviewResponseDto;
import sparta.ifour.movietalk.domain.review.service.ReviewQueryService;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewQueryController {

    private final ReviewQueryService reviewQueryService;

    /**
     * 리뷰 다건 조회
     * @param sort 정렬 순서, 필수값
     * qeury와 hashtag는 둘 다 없거나(전체 조회) 둘 중 하나만 있어야 한다.
     * @param query 검색어
     * @param hashtag 해시태그
     */
    @GetMapping("")
    public ResponseEntity<List<ReviewPreviewResponseDto>> getReviews(
        @RequestParam String sort,
        @RequestParam(required = false) String query,
        @RequestParam(required = false) String hashtag) {

        List<ReviewPreviewResponseDto> reviews = reviewQueryService.getReviews(sort, query, hashtag);

        return ResponseEntity.ok(reviews);
    }

    /**
     * 리뷰 단건 조회
     */
    @GetMapping("/{reviewId}") // 특정 리뷰 상세조회
    public ResponseEntity<ReviewDetailResponseDto> getReview(@PathVariable Long reviewId){
        ReviewDetailResponseDto reviewResponseDto = reviewQueryService.getReview(reviewId);
        return ResponseEntity.ok(reviewResponseDto);
    }
}
