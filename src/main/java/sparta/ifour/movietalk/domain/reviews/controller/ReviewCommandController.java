package sparta.ifour.movietalk.domain.reviews.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparta.ifour.movietalk.domain.reviews.dto.request.ReviewRequestDto;
import sparta.ifour.movietalk.domain.reviews.dto.response.ReviewPreviewResponseDto;
import sparta.ifour.movietalk.domain.reviews.service.ReviewService;
import sparta.ifour.movietalk.global.config.security.UserDetailsImpl;

/**
 * Review Create, Update, Delete 하는 기능을 가진 Controller
 */
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewCommandController {
    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewPreviewResponseDto> createReview(
            @RequestBody ReviewRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) { // 리뷰 생성

        ReviewPreviewResponseDto previewResponseDto = reviewService.createReview(requestDto, userDetails.getUser());

        return ResponseEntity.ok(previewResponseDto);
    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(@RequestBody ReviewRequestDto requestDto, @PathVariable Long reviewId) { // 리뷰 수정
        reviewService.updateReview(requestDto, reviewId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/{reviewId}/like")
    public ResponseEntity<?> clickLike(@PathVariable Long reviewId,
                                       @AuthenticationPrincipal UserDetailsImpl userDetails) {
        reviewService.clickLike(reviewId, userDetails.getUser());

        return ResponseEntity.ok().build();
    }
}
