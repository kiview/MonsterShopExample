/*
 * Example Spring-Boot project with multiple datasources and Spring-Boot test annotations.
 * Copyright (C) 2016  Kevin Wittek
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
