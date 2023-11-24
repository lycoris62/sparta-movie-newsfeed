package sparta.ifour.movietalk.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.ifour.movietalk.domain.review.entity.Review;


public interface ReviewRepository extends JpaRepository<Review, Long> {
}
