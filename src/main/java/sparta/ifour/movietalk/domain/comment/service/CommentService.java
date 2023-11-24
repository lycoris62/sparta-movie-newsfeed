package sparta.ifour.movietalk.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.ifour.movietalk.domain.comment.dto.request.CommentCreateRequestDto;
import sparta.ifour.movietalk.domain.comment.dto.request.CommentUpdateRequestDto;
import sparta.ifour.movietalk.domain.comment.dto.response.CommentCreateResponseDto;
import sparta.ifour.movietalk.domain.comment.entity.Comment;
import sparta.ifour.movietalk.domain.comment.repository.CommentRepository;
import sparta.ifour.movietalk.domain.review.entity.Review;
import sparta.ifour.movietalk.domain.review.repository.ReviewRepository;
import sparta.ifour.movietalk.domain.user.entity.User;
import sparta.ifour.movietalk.global.exception.InvalidAccessException;
import sparta.ifour.movietalk.global.exception.NotFoundException;

import java.util.concurrent.RejectedExecutionException;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final ReviewRepository reviewRepository;

    public CommentCreateResponseDto createComment(CommentCreateRequestDto requestDto, User user) {
        Review review = reviewRepository.findById(requestDto.getReviewId())
                .orElseThrow(() -> new NotFoundException());
        Comment comment = Comment.create(requestDto.getContent(), user, review);
        commentRepository.save(comment);
        return new CommentCreateResponseDto(comment);
    }

    public void updateComment(CommentUpdateRequestDto requestDto, Long commentId, User user) {
        Comment comment = getUserComment(commentId, user);
        comment.update(requestDto.getContent());
    }

    public void deleteComment(Long commentId, User user) {
        Comment comment = getUserComment(commentId, user);
        commentRepository.delete(comment);
    }

    private Comment getUserComment(Long commentId, User user) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new NotFoundException());

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new InvalidAccessException();
        }
        return comment;
    }
}
