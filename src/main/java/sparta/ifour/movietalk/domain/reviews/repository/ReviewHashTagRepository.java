package sparta.ifour.movietalk.domain.reviews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.ifour.movietalk.domain.reviews.entity.ReviewHashtag;

public interface ReviewHashTagRepository extends JpaRepository<ReviewHashtag,Long> {
}
