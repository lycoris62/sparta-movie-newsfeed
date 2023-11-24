package sparta.ifour.movietalk.domain.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.ifour.movietalk.domain.review.entity.ReviewHashtag;

public interface ReviewHashTagRepository extends JpaRepository<ReviewHashtag,Long> {
}
