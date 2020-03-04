package com.niit.qlsv.dao.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.niit.qlsv.dao.dto.BaseDto;
import com.niit.qlsv.dao.dto.RoleDto;
import lombok.*;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "role")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "role_code")
    private String code;

    @Column(name = "role_name")
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<UserEnt> users;

    public RoleDto getAsDto() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        return modelMapper.map(this, RoleDto.class);
    }
}
