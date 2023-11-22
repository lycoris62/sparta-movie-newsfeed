package sparta.ifour.movietalk.domain.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import sparta.ifour.movietalk.domain.user.dto.request.UserLoginRequestDto;
import sparta.ifour.movietalk.domain.user.dto.request.UserSignupRequestDto;
import sparta.ifour.movietalk.domain.user.service.AuthService;

/**
 * 인증 관련 컨트롤러.
 * 로그인 및 회원가입 처리
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class AuthController {

	private final AuthService authService;

	/**
	 * 회원가입
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@Valid @RequestBody UserSignupRequestDto request) {

		authService.signup(request);

		return ResponseEntity.ok().build();
	}

	/**
	 * 로그인
	 * @return JWT
	 */
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody UserLoginRequestDto request) {

		String token = authService.login(request);

		return ResponseEntity.ok()
			.header("Authorization", token)
			.build();
	}
}
