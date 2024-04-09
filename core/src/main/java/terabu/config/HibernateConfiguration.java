package terabu.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

//Пример для Spring data
//https://github.com/pkainulainen/spring-data-jpa-examples/blob/master/query-methods/src/main/java/net/petrikainulainen/springdata/jpa/config/PersistenceContext.java
@Configuration
@ComponentScan("terabu")
@EnableTransactionManagement
@PropertySource("classpath:config/application.properties")
@EnableJpaRepositories("terabu")
public class HibernateConfiguration {

    @Value("${hibernate.packagesToScan}")
    private String packagesToScan;
    @Value("${server.url}")
    private String url;
    @Value("${server.driver}")
    private String driver;
    @Value("${server.login}")
    private String login;
    @Value("${server.password}")
    private String pass;
    @Value("${hibernate.dialect}")
    private String dialect;
    @Value("${hibernate.show_sql}")
    private boolean showSql;
    @Value("${hibernate.format_sql}")
    private boolean formatSql;
    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddl;

    @Bean
    public DataSource dataSource() {
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setDriverClassName(driver);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(login);
        hikariConfig.setPassword(pass);
//        hikariConfig.setMaximumPoolSize();
        final HikariDataSource ds = new HikariDataSource(hikariConfig);
        return ds;
    }

    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", dialect);
        properties.put("hibernate.show_sql", showSql);
        properties.put("hibernate.format_sql", formatSql);
        properties.put("hibernate.hbm2ddl.auto", hbm2ddl);
        return properties;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setPackagesToScan(packagesToScan);
        entityManagerFactoryBean.setJpaProperties(hibernateProperties());
        return entityManagerFactoryBean;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManagerJpa=new JpaTransactionManager();
        transactionManagerJpa.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManagerJpa;
    }
}
