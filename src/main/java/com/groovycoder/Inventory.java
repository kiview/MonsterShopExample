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

package com.groovycoder;

import com.groovycoder.digimon.Digimon;
import com.groovycoder.digimon.Digivice;
import com.groovycoder.pokemon.Pokedex;
import com.groovycoder.pokemon.Pokemon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * TODO: add documentation
 */
@Component
public class Inventory {

    private final Digivice digivice;

    private final Pokedex pokedex;

    @Autowired
    public Inventory(Digivice digivice, Pokedex pokedex) {
        this.digivice = digivice;
        this.pokedex = pokedex;
    }

    @Transactional("digimonTransactionManager")
    public void addDigimon(Digimon digimon) {
        digivice.save(digimon);
    }

    @Transactional("pokemonTransactionManager")
    public void addPokemon(Pokemon pokemon) {
        pokedex.save(pokemon);
    }

}
