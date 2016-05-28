package com.groovycoder.pokemon;

import org.springframework.data.repository.CrudRepository;

/**
 * Spring data repository for {@link Pokemon} entities.
 */
public interface Pokedex extends CrudRepository<Pokemon, Long> {

}
