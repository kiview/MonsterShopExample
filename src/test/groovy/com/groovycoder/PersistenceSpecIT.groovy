package com.groovycoder

import com.groovycoder.digimon.Digimon
import com.groovycoder.digimon.Digivice
import com.groovycoder.pokemon.Pokedex
import com.groovycoder.pokemon.Pokemon
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * Integration tests for persistence layer.
 */
@ContextConfiguration // not mentioned by docs, but had to include this for Spock to startup the Spring context
@DataJpaTest
class PersistenceSpecIT extends Specification {

    @Autowired
    TestEntityManager testEntityManager

    @Autowired
    Pokedex pokedex

    @Autowired
    Digivice digivice

    def "fetches all persisted pokemon"() {
        given: "some pokemon"
        Pokemon bulbasaur = new Pokemon(1, "Bulbasaur", ["Grass", "Poison"])
        Pokemon charmander = new Pokemon(4, "Charmander", ["Fire"])

        and: "they are persisted"
        testEntityManager.persist(bulbasaur)
        testEntityManager.persist(charmander)

        when: "pokemon are fetched"
        List fetchedPokemon = pokedex.findAll().asList()

        then: "all persisted pokemon are returned"
        fetchedPokemon.size() == 2
        fetchedPokemon.containsAll([bulbasaur, charmander])
    }

    def "fetches all persisted digimon"() {
        given: "some digimon"
        Digimon agumon = new Digimon("Agumon", "Rookie", "Reptile", "Vaccine")
        Digimon greymon = new Digimon("Greymon", "Champion", "Dinosaur", "Vaccine")
        Digimon patamon = new Digimon("Patamon", "Rookie", "Mammal", "Data")

        and: "they are persisted"
        testEntityManager.persist(agumon)
        testEntityManager.persist(greymon)
        testEntityManager.persist(patamon)

        when: "digimon are fetched"
        List fetchedDigimon = digivice.findAll().asList()

        then: "all persisted digimon are returned"
        fetchedDigimon.size() == 3
        fetchedDigimon.containsAll([agumon, greymon, patamon])
    }

}
