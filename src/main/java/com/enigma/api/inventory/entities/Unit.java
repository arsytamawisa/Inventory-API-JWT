package com.enigma.api.inventory.entities;

import jdk.jfr.Name;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter @Setter @ToString
@Table
@Entity
public class Unit extends AbstractEntity {

    @Column(nullable = false, length = 5)
    private String code;

    @Column(nullable = false, length = 50)
    @Name("desc")
    private String description;
}
