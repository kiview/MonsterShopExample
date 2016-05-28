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
