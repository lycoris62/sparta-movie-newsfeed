package sparta.ifour.movietalk.domain.comment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.ifour.movietalk.domain.comment.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
