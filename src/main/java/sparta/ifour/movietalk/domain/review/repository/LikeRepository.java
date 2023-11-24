package sparta.ifour.movietalk.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.ifour.movietalk.domain.review.entity.Like;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByReviewIdAndUserId(Long reviewId, Long userId);

    List<Like> findAllByCreatedAtAfter(LocalDateTime dateTime);
}
