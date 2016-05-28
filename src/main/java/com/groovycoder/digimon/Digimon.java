package com.groovycoder.digimon;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * JPA domain entity representing a digimon.
 */
@Entity
public class Digimon {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    private String level;

    private String type;

    private String attribute;

    protected Digimon() {
    }

    public Digimon(String name, String level, String type, String attribute) {
        this.name = name;
        this.level = level;
        this.type = type;
        this.attribute = attribute;
    }
}
