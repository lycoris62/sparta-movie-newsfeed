package sparta.ifour.movietalk.global.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import sparta.ifour.movietalk.global.config.security.jwt.JwtFilter;
import sparta.ifour.movietalk.global.config.security.jwt.JwtUtil;

/**
 * 스프링 시큐리티 설정 객체
 * JWT 이용하여 인증 및 인가
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

	private final JwtUtil jwtUtil;
	private final UserDetailsService userDetailsService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtFilter jwtFilter() {
		return new JwtFilter(jwtUtil, userDetailsService);
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		http.csrf(AbstractHttpConfigurer::disable); // JWT 를 사용하므로 csrf 비활성화

		// 기본 설정인 Session 방식은 사용하지 않고 JWT 방식을 사용하기 위한 설정
		http.sessionManagement(sessionManager ->
			sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.authorizeHttpRequests(authz -> authz
			.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
			// 유저 도메인
			.requestMatchers(HttpMethod.PATCH, "/api/users/profile", "/api/users/password").authenticated()
			.requestMatchers("/api/users/**").permitAll()
			// 댓글 도메인
			.requestMatchers("/api/comments/**").authenticated()
			// 리뷰 도메인
			.requestMatchers(HttpMethod.GET, "/api/reviews/**").permitAll()
			.requestMatchers("/api/reviews/**").authenticated()

			.anyRequest().authenticated());

		http.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}
}
