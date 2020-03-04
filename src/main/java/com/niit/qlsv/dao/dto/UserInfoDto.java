package com.niit.qlsv.dao.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfoDto {
    private int id;
    private String firstName;
    private String lastName;
    private Date dayOfBirth;
    private String address;
    private String phone;
    private String email;
    private int gender;
}
