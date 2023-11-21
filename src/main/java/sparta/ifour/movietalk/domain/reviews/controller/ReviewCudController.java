package sparta.ifour.movietalk.domain.reviews.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.ifour.movietalk.domain.reviews.dto.ReviewRequestDto;
import sparta.ifour.movietalk.domain.reviews.dto.ReviewResponseDto;
import sparta.ifour.movietalk.domain.reviews.service.ReviewService;

/**
 * Review Create, Update, Delete 하는 기능을 가진 Controller
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewCudController {
    private final ReviewService reviewService;


    @PostMapping("/reviews")
    public ReviewResponseDto createReview(@RequestBody ReviewRequestDto requestDto) {
        return null;
    }

    @PatchMapping("/reviews/{reviewId}")
    public ReviewResponseDto updateReview(@PathVariable String reviewId) {
        return null;
    }

    @DeleteMapping("reviews/{reviewId}")
    public ReviewResponseDto deleteReview(@PathVariable String reviewId) {
        return null;
    }
}
