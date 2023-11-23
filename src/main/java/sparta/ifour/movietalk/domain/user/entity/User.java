package sparta.ifour.movietalk.domain.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import sparta.ifour.movietalk.domain.model.BaseEntity;
import sparta.ifour.movietalk.domain.user.dto.request.UserProfileUpdateRequestDto;
import sparta.ifour.movietalk.domain.user.dto.request.UserSignupRequestDto;

/**
 * 유저 엔티티.
 */
@Getter
@Entity
@Table(name = "users") // MySQL 에서 USER 는 예약어이므로 s를 붙임
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 에서는 기본 생성자가 필요하므로 최소 접근제어자로 생성
public class User extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 20, unique = true) // 4이상 20이하
	private String loginId; // 로그인 아이디

	@Column(nullable = false, length = 60) // 비밀버호, 8이상 20이하 이지만 BCrypt 암호화로 60자 제한.
	private String password;

	@Column(nullable = false, length = 20, unique = true) // 2이상 20이하
	private String nickname; // 사이트에서 표시될 닉네임

	@Column(length = 40) // null 가능, 40이하
	private String description; // 한 줄 소개

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserRoleEnum role;

	private User(String loginId, String password, String nickname, String description, UserRoleEnum role) {
		this.loginId = loginId;
		this.password = password;
		this.nickname = nickname;
		this.description = description;
		this.role = role;
	}

	/**
	 * 회원가입
	 */
	public static User create(UserSignupRequestDto requestDto) {
		return new User(requestDto.getLoginId(), requestDto.getPassword(), requestDto.getNickname(), requestDto.getDescription(), UserRoleEnum.USER);
	}

	/**
	 * 프로필 수정
	 */
	public void updateProfile(UserProfileUpdateRequestDto requestDto) {
		this.nickname = requestDto.getNickname();
		this.description = requestDto.getDescription();
	}

	/**
	 * 비밀번호 수정
	 */
	public void updatePassword(String encodedPassword) {
		this.password = encodedPassword;
	}
}
