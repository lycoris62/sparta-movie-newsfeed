package sparta.ifour.movietalk.domain.comment.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparta.ifour.movietalk.domain.comment.dto.request.CommentCreateRequestDto;
import sparta.ifour.movietalk.domain.comment.dto.request.CommentUpdateRequestDto;
import sparta.ifour.movietalk.domain.comment.dto.response.CommentCreateResponseDto;
import sparta.ifour.movietalk.domain.comment.service.CommentService;
import sparta.ifour.movietalk.global.config.security.UserDetailsImpl;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("")
    public ResponseEntity<CommentCreateResponseDto> createComment(
            @Valid @RequestBody CommentCreateRequestDto requestDto,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        CommentCreateResponseDto responseDto = commentService.createComment(requestDto, userDetails.getUser());
        return ResponseEntity.ok(responseDto);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<?> updateComment(
            @Valid @RequestBody CommentUpdateRequestDto requestDto,
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        commentService.updateComment(requestDto, commentId, userDetails.getUser());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {

        commentService.deleteComment(commentId, userDetails.getUser());
        return ResponseEntity.ok().build();
    }

}
