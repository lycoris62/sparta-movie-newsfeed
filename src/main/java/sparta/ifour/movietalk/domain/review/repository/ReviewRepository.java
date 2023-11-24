package sparta.ifour.movietalk.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.ifour.movietalk.domain.review.entity.Review;

import java.time.LocalDate;
import java.util.List;


public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByOrderByCreatedAtDesc();

    List<Review> findAllByCreatedAtAfterOrderByCreatedAtDesc(LocalDate date);
}
