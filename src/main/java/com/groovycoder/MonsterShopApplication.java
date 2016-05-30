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
import com.groovycoder.pokemon.Pokemon;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.h2.tools.Server;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.SQLException;
import java.util.Arrays;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class
})
@EnableTransactionManagement
public class MonsterShopApplication {

    private static final Log log = LogFactory.getLog(MonsterShopApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(MonsterShopApplication.class, args);
    }

    @Bean
    public Server createTcpServer() throws SQLException {
        return Server.createTcpServer().start();
    }

    @Profile("bootstrapData")
    @Bean
    public CommandLineRunner runner(Inventory inventory) {
        return args -> bootstrapData(inventory);
    }

    private void bootstrapData(Inventory inventory) {
        Pokemon bulbasaur = new Pokemon(1, "Bulbasaur", Arrays.asList("Grass", "Poison"));
        Pokemon charmander = new Pokemon(4, "Charmander", Arrays.asList("Fire"));

        inventory.addPokemon(bulbasaur);
        inventory.addPokemon(charmander);

        Digimon agumon = new Digimon("Agumon", "Rookie", "Reptile", "Vaccine");
        inventory.addDigimon(agumon);

        log.info("Persisting finished");
    }

}
