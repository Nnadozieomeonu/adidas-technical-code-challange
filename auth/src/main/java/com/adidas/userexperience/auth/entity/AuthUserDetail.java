package com.adidas.userexperience.auth.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthUserDetail extends User implements UserDetails {

    public AuthUserDetail(User user){
        super(user);
    }

    public AuthUserDetail(){

    }

    /**
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        getRoles().forEach(role -> {
            grantedAuthorityList.add(new SimpleGrantedAuthority(role.getName()));
            role.getPermissions().forEach(permission -> {
                grantedAuthorityList.add(new SimpleGrantedAuthority(permission.getName()));
            });
        });
        return grantedAuthorityList;
    }

    /**
     *
     * @return
     */
    @Override
    public String getPassword() {
        return super.getPassword();
    }

    /**
     *
     * @return
     */
    @Override
    public String getUsername() {
        return super.getUsername();
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isAccountNonExpired() {
        return super.isAccountNonExpired();
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isAccountNonLocked() {
        return super.isAccountNonLocked();
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return super.isCredentialsNonExpired();
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isEnabled() {
        return super.isEnabled();
    }
}