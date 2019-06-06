package com.jaybe.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

public class BaseEntity implements Serializable {

    @Getter
    @Setter
    private Long id;

}
