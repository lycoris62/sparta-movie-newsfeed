package sparta.ifour.movietalk.domain.user.dto.request;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * 회원가입 요청 DTO
 */
@Getter
@Setter
public class UserSignupRequestDto {

	@Pattern(regexp = "^[a-zA-Z0-9]{4,20}$") // a ~ z, A ~ Z, 0 ~ 9 만 포함, 4이상 20이하
	private String loginId;

	@Pattern(regexp = "^[a-zA-Z0-9!@#$]{8,20}$") // a ~ z, A ~ Z, 0 ~ 9, !@#$ 만 포함, 8이상 20이하
	private String password;

	@Pattern(regexp = "^[a-zA-Z0-9가-힣]{2,20}$") // a ~ z, A ~ Z, 0 ~ 9, 한글 만 포함, 2이상 20이하
	private String nickname;

	@Size(max = 40)
	private String description;
}