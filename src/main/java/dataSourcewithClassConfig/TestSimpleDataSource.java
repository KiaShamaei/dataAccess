package dataSourcewithClassConfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestSimpleDataSource {
    public static void main(String[] args) throws SQLException {
        var context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
        var dataSource = context
                .getBean("SimpleDriverManagerDataSource" , DataSource.class);
        Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement("select  * from users ");
        ResultSet result = statement.executeQuery();
        while (result.next()){
            System.out.println(result.getString("email"));
        }
    }
}
