package jpaConfigClass.config;


import com.mchange.v2.codegen.bean.Property;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"jpaConfigClass"})
@EnableTransactionManagement
@PropertySource("classpath:jdbc.properties")
public class Config {
    private final Environment environment;

    public Config(Environment environment) {
        this.environment = environment;
    }
    @Bean
    public DataSource dataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        dataSource.setUsername(environment.getProperty("jdbc.username"));
        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        return dataSource;

    }
    private Properties jpaProperties(){
        Properties props=new Properties();
        props.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        props.put("hibernate.show_sql",true);
        props.put("hibernate.format_sql",true);
        props.put("hibernate.use_sql_comments",true);
        props.put("hibernate.max_fetch_depth",3);
        props.put("hibernate.jdbc.fetch_size",50);
        props.put("hibernate.jdbc.batch_size",500);
        props.put("hibernate.hbm2ddl.auto","create");
        return props;
    }
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(){
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan("jpaConfigClass.entity");
        em.setJpaProperties(jpaProperties());
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        return em;
    }
    @Bean
    public JpaTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return  transactionManager;
    }
}
