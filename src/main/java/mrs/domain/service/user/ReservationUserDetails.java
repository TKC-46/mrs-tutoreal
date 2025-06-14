package mrs.domain.service.user;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import mrs.domain.model.User;

public class ReservationUserDetails implements UserDetails {

	private final User user;
	
	public ReservationUserDetails(User user) {
		this.user = user;
	}
	
	public User getUser() {
		return user;
	}
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// RoleName型のSpringSecurityのGrantedAuthorityに変換する。プレフィックスとしてROLE_を付ける
		return AuthorityUtils.createAuthorityList("ROLE_" + this.user.getRoleName().name());
	}

	@Override
	public String getPassword() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	@Override
	public String getUsername() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}

}
