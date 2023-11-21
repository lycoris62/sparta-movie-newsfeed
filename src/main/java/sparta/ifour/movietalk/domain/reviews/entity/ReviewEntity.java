package sparta.ifour.movietalk.domain.reviews.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@Data
@Builder
@Entity
@Table(name = "post")
@NoArgsConstructor
@AllArgsConstructor

public class ReviewEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Double ratingScore;

    @Column(nullable = false)
    private String movieName;


//    @ManyToOne // (fetch = FetchType.LAZY)
//    @JoinColumn(name = "userId")
//    @JsonIgnore
//    private User user;


}