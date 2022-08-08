package com.alireza.sadeghi.springboot.domain;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Privilege {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "privileges")
    private Collection<Role> roles;
}
