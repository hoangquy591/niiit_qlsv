package com.niit.qlsv.dao.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class LoginResponse implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;

    private String username;
    private String token;
}
