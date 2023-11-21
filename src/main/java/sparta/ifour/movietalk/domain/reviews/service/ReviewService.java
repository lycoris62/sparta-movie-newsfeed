package sparta.ifour.movietalk.domain.reviews.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.ifour.movietalk.domain.reviews.dto.ReviewResponseDto;
import sparta.ifour.movietalk.domain.reviews.entity.Review;
import sparta.ifour.movietalk.domain.reviews.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewResponseDto getReview(Long reviewId) {

        Review review = getReviewById(reviewId);

        return new ReviewResponseDto(review);

    }

    public List<ReviewResponseDto> getReviewsAll() {

        List<Review> reviewListAll = reviewRepository.findAll();

        return reviewListAll.stream()
                .map(ReviewResponseDto::new)
                .toList();

    }

    public List<ReviewResponseDto> getReviewsBySearch(String queryname){

        List<Review> reviewListAll = reviewRepository.findAll();
        List<Review> reviewListBySearch = new ArrayList<>();

        for(Review rev : reviewListAll){
            if(rev.getContent().contains(queryname) || rev.getTitle().contains(queryname) || rev.getMovieName().contains(queryname))
            {
                reviewListBySearch.add(rev);
            }
        }

        return reviewListBySearch.stream()
                .map(ReviewResponseDto::new)
                .toList();

    }

    private Review getReviewById(Long id){
        return reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰글을 찾을 수 없습니다."));
    }



}
