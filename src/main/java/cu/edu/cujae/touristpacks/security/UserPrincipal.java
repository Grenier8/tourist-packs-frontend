package cu.edu.cujae.touristpacks.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cu.edu.cujae.touristpacks.dto.security.UserAuthenticatedDto;

public class UserPrincipal implements UserDetails {
    private int id;
    private String email;
    private String password;
    private boolean active;
    private String username;
    private String fullName;
    private String token;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(int id, String email, String password, boolean active, String token,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.active = active;
        this.token = token;
        this.authorities = authorities;
    }

    public static UserPrincipal create(UserAuthenticatedDto user) {
        List<GrantedAuthority> authorities;
        try {
            String roleName = user.getRole().getRoleName();
            // Collection<String> roleNames = user.getRoles().stream().map(role ->
            // role.getRoleName())
            // .collect(Collectors.toList());
            authorities = AuthorityUtils.createAuthorityList(roleName);
        } catch (Exception e) {
            authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

        }
        return new UserPrincipal(
                user.getIdUser(),
                user.getEmail(),
                user.getPassword(),
                true,
                user.getToken(),
                authorities);
    }

    public static UserPrincipal create(UserAuthenticatedDto user, Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        return userPrincipal;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return active;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getFullName() {
        return fullName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return this.active;
    }

    public boolean getActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
