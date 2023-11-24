package sparta.ifour.movietalk.domain.user.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

/**
 * 비밀번호를 수정하는 요청 DTO
 * 비밀번호를 한번 더 검증 후 새 비밀번호로 변경
 */
@Getter
@Setter
public class UserPasswordUpdateRequestDto {

	@Pattern(
			regexp = "^[a-zA-Z0-9!@#$]{8,20}$",
			message = "비밀번호는 a ~ z, A ~ Z, 0 ~ 9, !@#$ 만 포함, 8이상 20이하") // a ~ z, A ~ Z, 0 ~ 9, !@#$ 만 포함, 8이상 20이하
	private String oldPassword;

	@Pattern(regexp = "^[a-zA-Z0-9!@#$]{8,20}$",
			message = "비밀번호는 a ~ z, A ~ Z, 0 ~ 9, !@#$ 만 포함, 8이상 20이하") // a ~ z, A ~ Z, 0 ~ 9, !@#$ 만 포함, 8이상 20이하
	private String newPassword;
}
