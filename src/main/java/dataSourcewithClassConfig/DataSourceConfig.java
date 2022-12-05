package dataSourcewithClassConfig;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DriverManagerDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Driver;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class DataSourceConfig {
    private final Environment environment;

    public DataSourceConfig(Environment environment) {
        this.environment = environment;
    }
    //config with spring jdbc driver 1 DriverManager
    @Bean("DriverManagerDataSource")
    public DataSource getWithDriverManager(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/userdb");
        dataSource.setPassword("KM@852147154");
        dataSource.setUser("root");
        return dataSource;
    }
    //config with spring jdbc driver 2 simpleDriverManager
    @Bean("SimpleDriverManagerDataSource")
    public DataSource getWithSimpleDirverManager() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource=new SimpleDriverDataSource();
        Class<? extends Driver> driver = (Class<? extends Driver>) Class.forName("com.mysql.cj.jdbc.Driver");
        dataSource.setDriverClass(driver);
        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        dataSource.setUsername(environment.getProperty("jdbc.username"));
        return dataSource;
    }
    //config with apache common dataSource
    public DataSource getWithApacheDCP2() throws PropertyVetoException {
        ComboPooledDataSource  dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        dataSource.setUser(environment.getProperty("jdbc.username"));
        dataSource.setDriverClass(environment.getProperty("jdbc.driverClassName"));
        return dataSource;
    }
//make dataSource with hikari
    @Bean(name = "hikari")
    public DataSource hikariDataSource() throws PropertyVetoException {
        return new HikariDataSource(hikariConfig());
    }

    // @Bean
    public HikariConfig hikariConfig()
    {
        HikariConfig hikariConfig=new HikariConfig();
        hikariConfig.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        hikariConfig.setJdbcUrl(environment.getProperty("jdbc.url"));
        hikariConfig.setUsername(environment.getProperty("jdbc.username"));
        hikariConfig.setPassword(environment.getProperty("jdbc.password"));
        return  hikariConfig;
    }

    @Bean("hikari2")
    public DataSource hikariConfigTest2(){
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        dataSource.setUsername(environment.getProperty("jdbc.username"));
        dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        return dataSource;
    }
}
