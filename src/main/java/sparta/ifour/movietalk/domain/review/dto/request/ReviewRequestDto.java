package sparta.ifour.movietalk.domain.review.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ReviewRequestDto {
    @Size(min = 1, max = 50)
    @NotBlank
    private String title;

    @Size(max = 1000)
    @NotBlank
    private String content;

    @Max(10)
    @Min(0)
    @NotBlank
    private Float ratingScore;

    @Size(max = 100)
    @NotBlank
    private String movieName;
}
