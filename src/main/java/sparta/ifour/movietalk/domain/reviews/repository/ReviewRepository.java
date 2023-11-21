package sparta.ifour.movietalk.domain.reviews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.ifour.movietalk.domain.reviews.entity.Review;


public interface ReviewRepository extends JpaRepository<Review, Long> {
}
