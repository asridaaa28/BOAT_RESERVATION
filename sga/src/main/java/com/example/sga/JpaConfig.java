package com.example.sga;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class JpaConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource);

        // Set the packages to scan for @Entity classes
        entityManagerFactoryBean.setPackagesToScan("com.example.entities");

        // Set JPA Vendor Adapter (Hibernate in this case)
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);

        // Set additional Hibernate properties if needed
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        entityManagerFactoryBean.setJpaPropertyMap(properties);

        // Set the 'entityManagerFactoryInterface' to jakarta.persistence.EntityManagerFactory
        entityManagerFactoryBean.setEntityManagerFactoryInterface(jakarta.persistence.EntityManagerFactory.class);

        return entityManagerFactoryBean;
    }
}