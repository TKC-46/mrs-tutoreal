package mrs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Web連携機能(CSRF対策など)を有効
@EnableMethodSecurity// @PreAuthorize, @PostAuthorizeを有効にする
public class WebSecurityConfig {

	@Bean
	PasswordEncoder passwordEncoder() {
		// パスワードのエンコードアルゴリズムとしてBCryptを採用
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(authz -> authz
				.requestMatchers("/js/**", "/css/**").permitAll() // jsとcss配下のアクセスは常に許可
				.anyRequest().authenticated()// 上記以外は認証を要求
			).formLogin(login -> login// フォーム認証を行う以下それぞれの遷移先
				.loginPage("/loginForm")// ログイン画面
				.loginProcessingUrl("/login")// 認証URL
				.usernameParameter("username")// リクエストパラメータ
				.passwordParameter("password")
				.defaultSuccessUrl("/rooms", true)// trueにすることで認証成功時は常に/roomsへ遷移する
				.failureUrl("/loginForm?error=true").permitAll());// 認証失敗時のアクセスは常に許可
				// loginForm.htmlにerrorのパラメータをtrueで渡しアクセス
		return http.build();
	}
}
