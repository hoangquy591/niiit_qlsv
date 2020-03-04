package com.niit.qlsv.dao.dto;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
public class BaseDto implements Serializable {
    private int id;
}
