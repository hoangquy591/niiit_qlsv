package com.niit.qlsv.dao.entities;

import com.niit.qlsv.dao.dto.RoleDto;
import com.niit.qlsv.dao.dto.UserDto;
import com.niit.qlsv.dao.dto.UserInfoDto;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEnt implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

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

    @OneToOne
    @JoinColumn(name = "user_info")
    private UserInfoEnt userInfoEnt;

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

    public UserDto getAsDto() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        UserDto userDto = modelMapper.map(this, UserDto.class);
        List<RoleEnt> roleEntList = this.getRoles();
        if (roles != null) {
            List<RoleDto> roleDtoList = new ArrayList();
            roleEntList.forEach(e -> roleDtoList.add(e.getAsDto()));
            userDto.setRoles(roleDtoList);
        }
        userDto.setUserInfo(this.getUserInfoEnt().getAsDto());
        return userDto;
    }
}
