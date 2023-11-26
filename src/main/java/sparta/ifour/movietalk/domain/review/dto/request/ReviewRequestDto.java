package sparta.ifour.movietalk.domain.review.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;

@Getter
public class ReviewRequestDto {
    @Size(min = 1, max = 50, message = "리뷰의 제목은 1~50자 사이입니다.")
    @NotBlank(message = "리뷰의 제목은 공백이 불가능합니다.")
    private String title;

    @Size(max = 1000, message = "리뷰의 내용의 한도는 1000자입니다.")
    @NotBlank(message = "리뷰의 내용은 공백이 불가능합니다.")
    private String content;

    @Max(value = 10, message = "별점의 최댓값은 10입니다.")
    @Min(value = 0, message = "별점의 최솟값은 0입니다.")
    @NotNull(message = "별점은 필수 입력값입니다.")
    private Float ratingScore;

    @Size(max = 100, message = "영화 제목은 최대 100자입니다.")
    @NotBlank(message = "영화 제목은 필수 입력값입니다.")
    private String movieName;

    private List<String> tagList;
}
