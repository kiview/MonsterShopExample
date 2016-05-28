package com.groovycoder.digimon;

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
 * Datasource configuration for digimon package.
 */
@Configuration
@EnableJpaRepositories(basePackageClasses = Digimon.class,
        entityManagerFactoryRef = "digimonEntityManager",
        transactionManagerRef = "digimonTransactionManager")
public class DigimonConfig {

    @Bean
    @ConfigurationProperties(prefix = "datasource.digimon")
    public DataSource digimonDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean digimonEntityManager() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(digimonDataSource());
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan(this.getClass().getPackage().getName());
        factoryBean.setPersistenceUnitName("pokemon");

        return factoryBean;
    }

    @Bean
    public PlatformTransactionManager digimonTransactionManager() {
        return new JpaTransactionManager(digimonEntityManager().getObject());
    }

}
