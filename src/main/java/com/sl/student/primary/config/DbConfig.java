//package com.sl.student.primary.config;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import javax.sql.DataSource;
//
//@Configuration
//public class DbConfig {
//
//    @Value("${spring.datasource.db1.url}")
//    private String db1Url;
//
//    @Value("${spring.datasource.db1.username}")
//    private String db1Username;
//
//    @Value("${spring.datasource.db1.password}")
//    private String db1Password;
//
//    @Value("${spring.datasource.db2.url}")
//    private String db2Url;
//
//    @Value("${spring.datasource.db2.username}")
//    private String db2Username;
//
//    @Value("${spring.datasource.db2.password}")
//    private String db2Password;
//
//    @Bean(name = "dataSource1")
//    @Primary
//    public DataSource dataSource1() {
//        return createDataSource(db1Url, db1Username, db1Password);
//    }
//
//    @Bean(name = "dataSource2")
//    public DataSource dataSource2() {
//        return createDataSource(db2Url, db2Username, db2Password);
//    }
//
//    private DataSource createDataSource(String url, String username, String password) {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setUrl(url);
//        dataSource.setUsername(username);
//        dataSource.setPassword(password);
//        // Other settings if needed
//        return dataSource;
//    }
//}
