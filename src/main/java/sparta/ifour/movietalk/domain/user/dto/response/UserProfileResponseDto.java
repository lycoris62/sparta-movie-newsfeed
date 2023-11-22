package sparta.ifour.movietalk.domain.user.dto.response;

import lombok.Getter;
import lombok.Setter;
import sparta.ifour.movietalk.domain.user.entity.User;

/**
 * 프로필 응답 DTO
 */
@Getter
@Setter
public class UserProfileResponseDto {

	private String nickname;
	private String description;

	public UserProfileResponseDto(User user) {
		this.nickname = user.getNickname();
		this.description = user.getDescription();
	}
}
