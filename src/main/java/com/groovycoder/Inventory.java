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
