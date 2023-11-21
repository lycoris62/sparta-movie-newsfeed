package sparta.ifour.movietalk.domain.reviews.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sparta.ifour.movietalk.domain.reviews.dto.ReviewResponseDto;
import sparta.ifour.movietalk.domain.reviews.service.ReviewService;

/**
 * Review Create, Update, Delete 하는 기능을 가진 Controller
 */
@Controller
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewCudController {
    private final ReviewService reviewService;


}
