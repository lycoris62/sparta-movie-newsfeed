package sparta.ifour.movietalk.domain.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.ifour.movietalk.domain.comment.dto.request.CommentCreateRequestDto;
import sparta.ifour.movietalk.domain.comment.dto.response.CommentCreateResponseDto;
import sparta.ifour.movietalk.domain.comment.entity.Comment;
import sparta.ifour.movietalk.domain.comment.repository.CommentRepository;
import sparta.ifour.movietalk.domain.reviews.entity.Review;
import sparta.ifour.movietalk.domain.reviews.repository.ReviewRepository;
import sparta.ifour.movietalk.domain.user.entity.User;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final ReviewRepository reviewRepository;

    public CommentCreateResponseDto createComment(CommentCreateRequestDto requestDto, User user, Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new IllegalArgumentException("존재 하지 않는 리뷰 입니다."));
        Comment comment = Comment.create(requestDto.getContent(), user, review);
        commentRepository.save(comment);
        return new CommentCreateResponseDto(comment);
    }
}
