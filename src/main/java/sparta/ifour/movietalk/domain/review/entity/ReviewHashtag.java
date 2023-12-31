package sparta.ifour.movietalk.domain.review.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "review_hashtag")

public class ReviewHashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id", nullable = false)
    private Review review;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id", nullable = false)
    private Hashtag hashtag;

    public ReviewHashtag(Review review, Hashtag hashtag) {
        this.review = review;
        this.hashtag = hashtag;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public void setHashtag(Hashtag hashtag) {
        this.hashtag = hashtag;
    }
}
