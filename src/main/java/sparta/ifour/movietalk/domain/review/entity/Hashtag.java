package sparta.ifour.movietalk.domain.review.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name="hashtag")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Hashtag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(nullable = false, length = 15)
    private String name;

    @OneToMany(mappedBy = "hashtag")
    private List<ReviewHashtag> reviewHashtagList = new ArrayList<>();

    public Hashtag (String name) {
        this.name = name;
    }

    public void addReviewHashtag(ReviewHashtag reviewHashtag) {
        this.reviewHashtagList.add(reviewHashtag);
        reviewHashtag.setHashtag(this);
    }


}
