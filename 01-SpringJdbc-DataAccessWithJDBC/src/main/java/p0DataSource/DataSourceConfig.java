package p0DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.AllArgsConstructor;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.sql.Driver;

@Configuration
@AllArgsConstructor
@PropertySource("classpath:jdbc.properties")
public class DataSourceConfig {

    private Environment env;
    @Bean(name = "DriverManagerDataSource")
    public DataSource getDataSource()
    {
        DriverManagerDataSource dataSource=new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/myee");
        dataSource.setPassword("jdbc.driver.password");
        dataSource.setUsername("jdbc.driver.username");
        return dataSource;
    }
    @Bean(name = "SimpleDriverDataSource")
    public DataSource SimpleDriverDataSource() throws ClassNotFoundException {
        SimpleDriverDataSource dataSource=new SimpleDriverDataSource();
        Class<? extends Driver> driver = (Class<? extends Driver>) Class.forName("com.mysql.cj.jdbc.Driver");
        dataSource.setDriverClass(driver);
        dataSource.setUrl("jdbc:mysql://localhost:3306/myee");
        dataSource.setPassword("1234");
        dataSource.setUsername("root");
        return dataSource;
    }
    @Bean(name = "dbcp2")
    public DataSource dbcp2DataSource(){
        BasicDataSource dataSource=new BasicDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }
    @Bean(name = "c3p0")
    public DataSource c3p0DataSource() throws PropertyVetoException {
        ComboPooledDataSource dataSource=new ComboPooledDataSource();
        dataSource.setDriverClass(env.getProperty("jdbc.driverClassName"));
        dataSource.setJdbcUrl(env.getProperty("jdbc.url"));
        dataSource.setPassword(env.getProperty("jdbc.username"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }
    @Bean(name = "hikari")
    public DataSource hikariDataSource() throws PropertyVetoException {
        return new HikariDataSource(hikariConfig());
    }

   // @Bean
    public HikariConfig hikariConfig()
    {
        HikariConfig hikariConfig=new HikariConfig();
        hikariConfig.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        hikariConfig.setJdbcUrl(env.getProperty("jdbc.url"));
        hikariConfig.setUsername(env.getProperty("jdbc.username"));
        hikariConfig.setPassword(env.getProperty("jdbc.password"));
        return  hikariConfig;
    }
}
