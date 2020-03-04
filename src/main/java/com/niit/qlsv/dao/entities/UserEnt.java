package com.niit.qlsv.dao.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.niit.qlsv.dao.dto.BaseDto;
import com.niit.qlsv.dao.dto.RoleDto;
import com.niit.qlsv.dao.dto.UserDto;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEnt extends BaseEnt implements UserDetails {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "user_roles",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleEnt> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<RoleEnt> roles = this.getRoles();
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEnt role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getCode()));
        }
        return authorities;
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

    @Override
    public UserDto getAsDto() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        UserDto userDto = modelMapper.map(this, UserDto.class);
        List<RoleEnt> roleEntList = this.getRoles();
        if (roles != null) {
            List<RoleDto> roleDtoList = new ArrayList();
            roleEntList.forEach(e -> {
                roleDtoList.add(e.getAsDto());
            });
            userDto.setRoles(roleDtoList);
        }
        return userDto;
    }
}
