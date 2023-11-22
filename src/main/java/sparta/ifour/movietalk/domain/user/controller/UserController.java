package sparta.ifour.movietalk.domain.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import sparta.ifour.movietalk.domain.user.dto.response.UserProfileResponseDto;
import sparta.ifour.movietalk.domain.user.service.UserService;

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
}
