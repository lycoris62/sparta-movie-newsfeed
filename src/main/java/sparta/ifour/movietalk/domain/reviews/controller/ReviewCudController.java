package sparta.ifour.movietalk.domain.reviews.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sparta.ifour.movietalk.domain.reviews.dto.ReviewRequestDto;
import sparta.ifour.movietalk.domain.reviews.dto.ReviewResponseDto;
import sparta.ifour.movietalk.domain.reviews.service.ReviewService;

/**
 * Review Create, Update, Delete 하는 기능을 가진 Controller
 */
@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewCudController {
    private final ReviewService reviewService;


    @PostMapping
    public ReviewResponseDto createReview(@RequestBody ReviewRequestDto requestDto) {
        return reviewService.createReview(requestDto);
    }

    @PatchMapping("/{reviewId}")
    public ReviewResponseDto updateReview(@PathVariable String reviewId) {
        return null;
    }

    @DeleteMapping("/{reviewId}")
    public ReviewResponseDto deleteReview(@PathVariable String reviewId) {
        return null;
    }
}
