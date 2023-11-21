package sparta.ifour.movietalk.domain.reviews.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.ifour.movietalk.domain.reviews.dto.ReviewRequestDto;
import sparta.ifour.movietalk.domain.reviews.dto.ReviewResponseDto;
import sparta.ifour.movietalk.domain.reviews.entity.Review;
import sparta.ifour.movietalk.domain.reviews.repository.ReviewRepository;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
}
