package sparta.ifour.movietalk.domain.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import sparta.ifour.movietalk.domain.user.dto.request.UserLoginRequestDto;
import sparta.ifour.movietalk.domain.user.dto.request.UserSignupRequestDto;
import sparta.ifour.movietalk.domain.user.entity.User;
import sparta.ifour.movietalk.domain.user.repository.UserRepository;
import sparta.ifour.movietalk.global.config.security.jwt.JwtUtil;
import sparta.ifour.movietalk.global.exception.NotFoundException;

/**
 * 사용자 관련 서비스
 */
@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

	private final JwtUtil jwtUtil;
	private final PasswordEncoder passwordEncoder;
	private final UserRepository userRepository;

	/**
	 * 회원가입
	 */
	@Transactional
	public void signup(UserSignupRequestDto requestDto) {

		validateDuplicateId(requestDto);

		requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
		User user = User.create(requestDto);

		userRepository.save(user);
	}

	private void validateDuplicateId(UserSignupRequestDto requestDto) {
		if (userRepository.existsByLoginIdOrNickname(requestDto.getLoginId(), requestDto.getNickname())) {
			throw new IllegalArgumentException("이미 있는 아이디");
		}
	}

	/**
	 * 로그인
	 * @return JWT
	 */
	public String login(UserLoginRequestDto requestDto) {

		User user = userRepository.findByLoginId(requestDto.getLoginId())
			.orElseThrow((NotFoundException::new));

		validatePassword(requestDto.getPassword(), user.getPassword());

		return jwtUtil.createToken(user.getNickname(), user.getRole());
	}

	private void validatePassword(String rawPassword, String userPassword) {
		if (!passwordEncoder.matches(rawPassword, userPassword)) {
			throw new IllegalArgumentException("잘못된 비밀번호");
		}
	}
}
