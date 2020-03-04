package com.niit.qlsv.dao.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto extends BaseDto {
    private String username;
    private List<RoleDto> roles;
}
