package com.madeleine.madeleine.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CustomUserDetails implements UserDetails {
    private User user;

    public Long getId(){
        return user.getId();
    }
    
    @Override
    public String getUsername(){
        return user.getUsername();
    }

    public Set<NewsLetter> getSubscribes(){
        return user.getSubscribes();
    }

    public Set<Category> getCategories(){
        return user.getCategories();
    }

    @Override
    public boolean isEnabled(){
        return user.getIsEnabled();
    }

    @Override
    public String getPassword(){
        return user.getPassword();
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return user.getIsEnabled();
    }
    
    @Override
    public Collection<GrantedAuthority> getAuthorities(){
        List<GrantedAuthority> authorites = new ArrayList<>();
        authorites.add(new SimpleGrantedAuthority(user.getAuthority()));
        return authorites;
    }

    @Override
    public boolean isAccountNonExpired(){
        return user.getIsEnabled();
    }

    @Override
    public boolean isAccountNonLocked(){
        return user.getIsEnabled();
    }
}
