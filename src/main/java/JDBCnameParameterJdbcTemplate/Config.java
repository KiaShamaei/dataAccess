package JDBCnameParameterJdbcTemplate;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

@Configuration
@ComponentScan(basePackages = "JDBCnameParameterJdbcTemplate")
@PropertySource("classpath:jdbc.properties")
public class Config {
    private final Environment environment;

    public Config(Environment environment) {
        this.environment = environment;
    }

    @Bean("dataSource")
    public DataSource getWithApacheDCP2() throws PropertyVetoException {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setJdbcUrl(environment.getProperty("jdbc.url"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        dataSource.setUser(environment.getProperty("jdbc.username"));
        dataSource.setDriverClass(environment.getProperty("jdbc.driverClassName"));
        return dataSource;
    }
}
