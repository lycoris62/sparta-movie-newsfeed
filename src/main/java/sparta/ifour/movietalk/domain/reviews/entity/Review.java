package sparta.ifour.movietalk.domain.reviews.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import sparta.ifour.movietalk.domain.model.BaseEntity;
import sparta.ifour.movietalk.domain.user.entity.User;

import java.util.ArrayList;
import java.util.List;


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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "review")
    private List<ReviewHashtag> reviewHashtagList = new ArrayList<>();

    @OneToMany(mappedBy = "like")
    private List<Like> likeList = new ArrayList<>();

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

    public void addReviewHashtag(ReviewHashtag reviewHashtag) {
        this.reviewHashtagList.add(reviewHashtag);
        reviewHashtag.setReview(this);
    }

    public void addLike(Like like) {
        this.likeList.add(like);
        like.setReview(this);
    }

    public void removeLike(Like like) {
        this.likeList.remove(like);
        like.setReview(null);
    }
}