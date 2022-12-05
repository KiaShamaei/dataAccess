package dataSourcewithClassConfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDriverManagerDataSource {
    public static void main(String[] args) throws SQLException {
        var context=new AnnotationConfigApplicationContext(DataSourceConfig.class);
        final DataSource myDataSource = context.getBean("DriverManagerDataSource", DataSource.class);
        final Connection connection = myDataSource.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("select * from users");
        final ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
            System.out.println(resultSet.getString("email"));
    }
}
