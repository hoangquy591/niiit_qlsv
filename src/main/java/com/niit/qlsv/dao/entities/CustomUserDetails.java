//package com.niit.qlsv.dao.entities;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.*;
//
//@Data
//@AllArgsConstructor
//public class CustomUserDetails implements UserDetails {
//
//    private UserEnt user;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Collection<RoleEnt> roles = user.getRoles();
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        for (RoleEnt role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role.getCode()));
//        }
//        return authorities;
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        return user.getUsername();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
//}
