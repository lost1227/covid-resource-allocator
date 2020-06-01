package edu.calpoly.csc_308.cora.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;

import edu.calpoly.csc_308.cora.entities.User;

import lombok.Data;

public class AuthUser extends org.springframework.security.core.userdetails.User {

  @Data
  public static class Auth {
    private final String username;
    private final String password;
    private final boolean enabled;
    private final boolean accountNonExpired;
    private final boolean credentialsNonExpired;
    private final boolean accountNonLocked;
    private final Collection<? extends GrantedAuthority> authorities;

    public Auth(String username, String password) {
      this.username = username;
      this.password = password;
      this.enabled = true;
      this.accountNonExpired = true;
      this.credentialsNonExpired = true;
      this.accountNonLocked = true;
      this.authorities = new ArrayList<>();
    }
  }

  private static final long serialVersionUID = 1L;
  
  private User user;
  
  public AuthUser(
    Auth auth,
    User user
  ) {
    super(auth.getUsername(), auth.getPassword(), auth.isEnabled(), auth.isAccountNonExpired(), auth.isCredentialsNonExpired(), auth.isAccountNonLocked(), auth.getAuthorities());
    if(user == null) {
      throw new IllegalArgumentException("Cannot have null user");
    }
    this.user = user;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return super.toString() + " ; " + user.toString();
  }

  @Override
  public boolean equals(Object other) {
    if(other instanceof AuthUser) {
      AuthUser o = (AuthUser) other;
      return super.equals(o) && user.equals(o.user);
    } else {
      return false;
    }
  }

  @Override
  public int hashCode() {
    return Objects.hash(super.hashCode(), this.user);
  }
}
