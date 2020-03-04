package com.niit.qlsv.dao.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private int id;
    private String username;
    private List<RoleDto> roles;
    private UserInfoDto userInfo;
}
