package sparta.ifour.movietalk.domain.reviews.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @OneToMany(mappedBy = "hashtage")
    private List<ReviewHashtag> reviewHashtagList = new ArrayList<>();

}
