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

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Configuration class for pokemon package.
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = Pokemon.class,
        entityManagerFactoryRef = "pokemonEntityManager",
        transactionManagerRef = "pokemonTransactionManager")
public class PokemonConfig {

    @Bean
    @ConfigurationProperties(prefix = "datasource.pokemon")
    public DataSource pokemonDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean pokemonEntityManager() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(pokemonDataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan(this.getClass().getPackage().getName());
        factoryBean.setPersistenceUnitName("pokemon");

        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager pokemonTransactionManager() {
        return new JpaTransactionManager(pokemonEntityManager().getObject());
    }

}
