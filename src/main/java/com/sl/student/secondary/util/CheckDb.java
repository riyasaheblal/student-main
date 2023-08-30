package com.sl.student.secondary.util;

import com.sl.student.primary.config.DatasourceConfig;
import jakarta.persistence.EntityManager;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

public class CheckDb {


    public static void main(String[] args) {

        LocalContainerEntityManagerFactoryBean bds = entityManagerFactory();
        //EntityManager entityManager = bds.getObject();
        System.out.println(bds);

        //DatasourceConfig dbConfig = new DatasourceConfig();
        //dbConfig.connect("jdbc:mysql://192.168.101.134:3306/storedproducer","testpp","testpp");
    }

    private static BasicDataSource getBasicDataSource(String classForName, String url, String userName, String password){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://192.168.101.134:3306/storedproducer");
        dataSource.setUsername("testpp");
        dataSource.setPassword("testpp");

        // Set connection pool settings
        dataSource.setInitialSize(10);   // Initial number of connections
        dataSource.setMaxTotal(50);
        return dataSource;
    }

    public static LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(getBasicDataSource("","","",""));
        entityManagerFactoryBean.setPackagesToScan("com.example.model"); // Replace with your package containing JPA entities
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        // Additional JPA properties (if needed)
        // entityManagerFactoryBean.setJpaProperties(jpaProperties);
        return entityManagerFactoryBean;
    }

}
