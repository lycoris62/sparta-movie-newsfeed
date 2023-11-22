package sparta.ifour.movietalk.domain.user.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * 유저 정보를 수정하는 요청 DTO
 * 요청 시, 변경되지 않은 필드라면 원래 필드 값을 보내야 한다.
 */
@Getter
@Setter
public class UserProfileUpdateRequestDto {

	@Pattern(regexp = "^[a-zA-Z0-9가-힣]{2,20}$") // a ~ z, A ~ Z, 0 ~ 9, 한글 만 포함, 2이상 20이하
	private String nickname;

	private String description;
}
