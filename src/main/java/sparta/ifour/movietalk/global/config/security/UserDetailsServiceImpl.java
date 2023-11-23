package sparta.ifour.movietalk.global.config.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import sparta.ifour.movietalk.domain.user.entity.User;
import sparta.ifour.movietalk.domain.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByNickname(username)
			.orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));

		return new UserDetailsImpl(user);
	}
}
