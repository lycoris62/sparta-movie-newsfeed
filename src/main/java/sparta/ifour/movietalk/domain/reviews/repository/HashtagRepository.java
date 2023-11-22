package sparta.ifour.movietalk.domain.reviews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.ifour.movietalk.domain.reviews.entity.Hashtag;

public interface HashtagRepository extends JpaRepository<Hashtag,Long> {
}
