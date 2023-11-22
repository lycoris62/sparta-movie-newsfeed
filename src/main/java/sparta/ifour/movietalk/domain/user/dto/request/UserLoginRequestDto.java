package sparta.ifour.movietalk.domain.user.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * 로그인 요청 DTO
 */
@Getter
@Setter
public class UserLoginRequestDto {

	@Pattern(regexp = "^[a-zA-Z0-9]{4,20}$") // a ~ z, A ~ Z, 0 ~ 9 만 포함, 4이상 20이하
	private String loginId;

	@Pattern(regexp = "^[a-zA-Z0-9가-힣]{2,20}$") // a ~ z, A ~ Z, 0 ~ 9, 한글 만 포함, 2이상 20이하
	private String password;
}
