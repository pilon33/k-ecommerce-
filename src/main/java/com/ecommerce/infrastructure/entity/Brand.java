package com.ecommerce.infrastructure.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author jfvaldez
 */
@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy="brand",cascade=CascadeType.ALL)
    private Set<Product> productEntities = new HashSet<>();
}
