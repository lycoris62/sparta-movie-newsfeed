package sparta.ifour.movietalk.domain.user.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRoleEnum {

	USER(Authority.USER);  // 사용자 권한

	private final String authority;

	public static class Authority {
		public static final String USER = "ROLE_USER";
	}
}