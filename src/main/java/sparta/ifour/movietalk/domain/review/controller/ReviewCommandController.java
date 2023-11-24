package sparta.ifour.movietalk.domain.review.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sparta.ifour.movietalk.domain.review.dto.request.ReviewRequestDto;
import sparta.ifour.movietalk.domain.review.dto.response.ReviewPreviewResponseDto;
import sparta.ifour.movietalk.domain.review.service.ReviewCommandService;
import sparta.ifour.movietalk.global.config.security.UserDetailsImpl;

/**
 * Review Create, Update, Delete 하는 기능을 가진 Controller
 */
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewCommandController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping
    public ResponseEntity<ReviewPreviewResponseDto> createReview(
            @Valid @RequestBody ReviewRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) { // 리뷰 생성

        ReviewPreviewResponseDto previewResponseDto = reviewCommandService.createReview(requestDto, userDetails.getUser());

        return ResponseEntity.ok(previewResponseDto);
    }

    @PatchMapping("/{reviewId}")
    public ResponseEntity<?> updateReview(
            @Valid @RequestBody ReviewRequestDto requestDto,
            @PathVariable Long reviewId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) { // 리뷰 수정

        reviewCommandService.updateReview(requestDto, reviewId, userDetails.getUser());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReview(
            @PathVariable Long reviewId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) { // 리뷰 삭제

        reviewCommandService.deleteReview(reviewId, userDetails.getUser());

        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{reviewId}/like")
    public ResponseEntity<?> clickLike(
            @PathVariable Long reviewId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        reviewCommandService.clickLike(reviewId, userDetails.getUser());

        return ResponseEntity.ok().build();
    }
}
