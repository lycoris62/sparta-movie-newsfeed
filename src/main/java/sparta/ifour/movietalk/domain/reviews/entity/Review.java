package sparta.ifour.movietalk.domain.reviews.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import sparta.ifour.movietalk.domain.model.BaseEntity;
import sparta.ifour.movietalk.domain.reviews.dto.request.ReviewRequestDto;
import sparta.ifour.movietalk.domain.reviews.dto.response.ReviewPreviewResponseDto;


@Getter
@Entity
@Table(name = "review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    @Length(max = 50)
    private String title;

    @Column(nullable = false)
    @Length(max = 1000)
    private String content;

    @Column(nullable = false)
    private Float ratingScore;

    @Column(nullable = false)
    @Length(max = 100)
    private String movieName;

    @Builder
    public Review(String title, String content, Float ratingScore, String movieName) {
        this.title = title;
        this.content = content;
        this.ratingScore = ratingScore;
        this.movieName = movieName;
    }

    public void update(String title, String content, Float ratingScore) {
        this.title = title;
        this.content = content;
        this.ratingScore = ratingScore;
    }
}