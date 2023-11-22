package sparta.ifour.movietalk.domain.comment.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparta.ifour.movietalk.domain.comment.dto.request.CommentUpdateRequestDto;
import sparta.ifour.movietalk.domain.comment.dto.request.CommentCreateRequestDto;
import sparta.ifour.movietalk.domain.comment.dto.response.CommentCreateResponseDto;
import sparta.ifour.movietalk.domain.comment.service.CommentService;
import sparta.ifour.movietalk.domain.user.entity.User;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{reviewId}")
    public ResponseEntity<CommentCreateResponseDto> createComment(@RequestBody CommentCreateRequestDto requestDto, @PathVariable Long reviewId, @AuthenticationPrincipal User user) {
        CommentCreateResponseDto responseDto = commentService.createComment(requestDto, user, reviewId);
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity updateComment(@RequestBody CommentUpdateRequestDto requestDto, @PathVariable Long commentId, @AuthenticationPrincipal User user) {
        commentService.updateComment(requestDto, commentId, user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity deleteComment(@PathVariable Long commentId, @AuthenticationPrincipal User user) {
        commentService.deleteComment(commentId, user);
        return ResponseEntity.ok().build();
    }

}
