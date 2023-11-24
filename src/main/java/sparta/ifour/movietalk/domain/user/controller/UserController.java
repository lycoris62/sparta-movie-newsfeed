package sparta.ifour.movietalk.domain.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import sparta.ifour.movietalk.domain.user.dto.request.UserPasswordUpdateRequestDto;
import sparta.ifour.movietalk.domain.user.dto.request.UserProfileUpdateRequestDto;
import sparta.ifour.movietalk.domain.user.dto.response.UserProfileResponseDto;
import sparta.ifour.movietalk.domain.user.service.UserService;
import sparta.ifour.movietalk.global.config.security.UserDetailsImpl;

/**
 * 인증을 제외한 유저 관련 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	/**
	 * 프로필 조회
	 */
	@GetMapping("/{nickname}")
	public ResponseEntity<UserProfileResponseDto> getProfile(@PathVariable String nickname) {

		UserProfileResponseDto responseDto = userService.getProfile(nickname);

		return ResponseEntity.ok(responseDto);
	}

	@PatchMapping("/profile")
	public ResponseEntity<?> updateProfile(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@Valid @RequestBody UserProfileUpdateRequestDto requestDto) {

		userService.updateProfile(userDetails.getUsername(), requestDto);

		return ResponseEntity.ok().build();
	}

	@PatchMapping("/password")
	public ResponseEntity<?> updatePassword(
		@AuthenticationPrincipal UserDetailsImpl userDetails,
		@Valid @RequestBody UserPasswordUpdateRequestDto requestDto) {

		userService.updatePassword(userDetails.getUsername(), requestDto);

		return ResponseEntity.ok().build();
	}
}
