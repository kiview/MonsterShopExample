package com.groovycoder.digimon;

import org.springframework.data.repository.CrudRepository;

/**
 * Spring data repository for {@link Digimon} entities.
 */
public interface Digivice extends CrudRepository<Digimon, Long> {
}
