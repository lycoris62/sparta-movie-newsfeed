package sparta.ifour.movietalk.domain.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import sparta.ifour.movietalk.domain.user.dto.request.UserPasswordUpdateRequestDto;
import sparta.ifour.movietalk.domain.user.dto.request.UserProfileUpdateRequestDto;
import sparta.ifour.movietalk.domain.user.dto.response.UserProfileResponseDto;
import sparta.ifour.movietalk.domain.user.entity.User;
import sparta.ifour.movietalk.domain.user.repository.UserRepository;

/**
 * 인증을 제외한 유저 관련 서비스
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	/**
	 * 프로필 조회
	 */
	public UserProfileResponseDto getProfile(String nickname) {

		User user = userRepository.findByNickname(nickname)
			.orElseThrow(() -> new IllegalArgumentException("잘못된 닉네임"));

		return new UserProfileResponseDto(user);
	}

	/**
	 * 프로필 수정
	 */
	@Transactional
	public void updateProfile(User user, UserProfileUpdateRequestDto request) {

		user.updateProfile(request);
	}

	/**
	 * 비밀번호 수정
	 */
	@Transactional
	public void updatePassword(String nickname, UserPasswordUpdateRequestDto request) {

		User user = userRepository.findByNickname(nickname)
			.orElseThrow(IllegalArgumentException::new);

		String encodedPassword = passwordEncoder.encode(request.getNewPassword());
		validatePassword(request.getOldPassword(), user.getPassword());

		user.updatePassword(encodedPassword);
	}

	private void validatePassword(String rawPassword, String userPassword) {
		if (!passwordEncoder.matches(rawPassword, userPassword)) {
			throw new IllegalArgumentException("잘못된 비밀번호");
		}
	}
}
