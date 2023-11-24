package sparta.ifour.movietalk.domain.review.enums;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum ReviewSort {

	RECENT("recent"),

	LIKE_TODAY("likeToday"),
	LIKE_WEEK("likeWeek"),
	LIKE_MONTH("likeWeek"),
	LIKE_YEAR("likeYear");

	private final String name;

	ReviewSort(String name) {
		this.name = name;
	}

	public static ReviewSort parse(String sort) {
		return Arrays.stream(ReviewSort.values())
			.filter(reviewSort -> reviewSort.getName().equals(sort))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("없는 정렬 방식"));
	}
}
