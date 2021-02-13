package com.enigma.api.inventory.entities;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Getter @Setter @ToString
@Table
@Entity
public class Item extends AbstractEntity {

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne
    @JoinColumn(name = "unit_id", nullable = false)
    private Unit unit;
}
