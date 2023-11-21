package sparta.ifour.movietalk.domain.reviews.entity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;


@Getter
@Entity
@Table(name = "review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Review{
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
    private Double ratingScore;

    @Column(nullable = false)
    @Length(max = 100)
    private String movieName;

}