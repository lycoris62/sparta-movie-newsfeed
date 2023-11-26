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

	@Pattern(regexp = "^[a-zA-Z0-9]{4,20}$",
			message = "로그인 아이디는 a ~ z, A ~ Z, 0 ~ 9 만 포함, 4이상 20이하") // a ~ z, A ~ Z, 0 ~ 9 만 포함, 4이상 20이하
	private String loginId;

	@Pattern(regexp = "^[a-zA-Z0-9!@#$]{8,20}$",
			message = "비밀번호는 a ~ z, A ~ Z, 0 ~ 9, !@#$ 만 포함, 8이상 20이하") // a ~ z, A ~ Z, 0 ~ 9, !@#$ 만 포함, 8이상 20이하
	private String password;

	@Pattern(regexp = "^[a-zA-Z0-9가-힣]{2,20}$",
			message = "닉네임은 a ~ z, A ~ Z, 0 ~ 9, 한글 만 포함, 2이상 20이하") // a ~ z, A ~ Z, 0 ~ 9, 한글 만 포함, 2이상 20이하
	private String nickname;

	@Size(max = 40, message = "자기소개는 최대 40자입니다.")
	private String description;
}