package edu.calpoly.csc_308.cora.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import edu.calpoly.csc_308.cora.entities.User;

public class AuthUser extends org.springframework.security.core.userdetails.User {

    private static final long serialVersionUID = 1L;
    
    public User user;
    
    AuthUser(
        String username, 
        String password, 
        boolean enabled, 
        boolean accountNonExpired, 
        boolean credentialsNonExpired, 
        boolean accountNonLocked, 
        Collection<? extends GrantedAuthority> authorities,
        User user
    ) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        if(user == null) {
            throw new IllegalArgumentException("Cannot have null user");
        }
        this.user = user;
    }

    @Override
    public String toString() {
        return super.toString() + " ; " + user.toString();
    }
}
