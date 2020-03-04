package com.niit.qlsv.dao.entities;

import com.niit.qlsv.dao.dto.BaseDto;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@MappedSuperclass
public abstract class BaseEnt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public abstract BaseDto getAsDto();
}
