package com.groovycoder.pokemon;

import javax.persistence.*;
import java.util.List;

/**
 * JPA domain entity representing a pokemon.
 */
@Entity
public class Pokemon {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private Integer pokeIndex;

    @Column(unique = true)
    private String name;

    @ElementCollection
    private List<String> types;

    protected Pokemon() {
    }

    public Pokemon(Integer pokeIndex, String name, List<String> types) {
        this.pokeIndex = pokeIndex;
        this.name = name;
        this.types = types;
    }
}
