package sparta.ifour.movietalk.domain.reviews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.ifour.movietalk.domain.reviews.entity.Hashtag;

import java.util.Optional;

public interface HashtagRepository extends JpaRepository<Hashtag,Long> {
    Optional<Hashtag> findByName(String name);
}
