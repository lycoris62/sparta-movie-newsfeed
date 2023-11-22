package sparta.ifour.movietalk.domain.reviews.dto.response;

import lombok.Getter;
import sparta.ifour.movietalk.domain.comment.dto.response.CommentCreateResponseDto;
import sparta.ifour.movietalk.domain.reviews.entity.Review;

import java.util.List;

/**
 * 상세 조회 관련 ResponseDto
 */
@Getter
public class ReviewDetailResponseDto extends ReviewPreviewResponseDto {
    private List<CommentCreateResponseDto> commentList;

    public ReviewDetailResponseDto(Review review, List<CommentCreateResponseDto> commentList) {
        super(review);
        this.commentList = commentList;
    }
}
