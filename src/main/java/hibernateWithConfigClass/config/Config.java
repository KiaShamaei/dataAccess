package hibernateWithConfigClass.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@PropertySource("classpath:jdbc.properties")
@ComponentScan(basePackages = {"hibernateWithConfigClass"})
@EnableTransactionManagement
public class Config {
    private final Environment environment;

    public Config(Environment environment) {
        this.environment = environment;
    }
    @Bean
    public DataSource dataSource(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        dataSource.setUsername(environment.getProperty("jdbc.username"));
        dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        return dataSource;
    }
    private Properties hibernateProperties(){
        Properties props=new Properties();
        props.put("hibernate.dialect","org.hibernate.dialect.MySQL8Dialect");
        props.put("hibernate.show_sql",true);

        props.put("hibernate.format_sql",true);
        props.put("hibernate.use_sql_comments",true);
        props.put("hibernate.max_fetch_depth",3);
        props.put("hibernate.jdbc.fetch_size",50);
        props.put("hibernate.jdbc.batch_size",500);


          props.put("hibernate.hbm2ddl.auto","update");

        return props;

    }
    @Bean
    public SessionFactory mySession() throws IOException {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setHibernateProperties(hibernateProperties());
        sessionFactoryBean.setPackagesToScan("hibernateWithConfigClass.entities");
        sessionFactoryBean.afterPropertiesSet();

        return sessionFactoryBean.getObject();

    }
    @Bean
    public HibernateTransactionManager transactionManager() throws IOException {
        return new HibernateTransactionManager(mySession());
    }


}
