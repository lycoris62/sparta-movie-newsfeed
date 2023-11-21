package sparta.ifour.movietalk.domain.reviews.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ReviewRequestDto {
    @Size(max = 50)
    private String title;

    @Size(max = 1000)
    private String content;

    @Max(10)
    @Min(0)
    @NotBlank
    private Float ratingScore;

    @Size(max = 100)
    private String movieName;
}
