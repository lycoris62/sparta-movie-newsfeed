package sparta.ifour.movietalk.domain.reviews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.ifour.movietalk.domain.reviews.entity.Like;

public interface LikeRepository extends JpaRepository<Like, Long> {
}
