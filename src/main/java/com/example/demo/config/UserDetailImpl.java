package com.example.demo.config;

import com.example.demo.models.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailImpl implements UserDetails {

    private Long id ;
    private String username ;
    private String email;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailImpl(Long id, String name, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = name;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }
    public static UserDetailImpl build(User user){
        List<GrantedAuthority> authorities = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

        return new UserDetailImpl(user.getUserID(),user.getUserFirstName(),user.getUserEmail(), user.getUserPassword(),authorities);
    }
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        return true ;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true ;
    }

    @Override
    public boolean isEnabled() {
        return true ;
    }
    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o == null || getClass() != o.getClass())
            return false;
        UserDetailImpl user = (UserDetailImpl) o;
        return Objects.equals(id,user.id);
    }

}
