package dataSourcewithClassConfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestHIkariDataSource {
    public static void main(String[] args) throws SQLException {
        var context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
//        var dataSourceBean = context.getBean("hikari" , DataSource.class);
        var dataSourceBean = context.getBean("hikari2" , DataSource.class);
        Connection connection = dataSourceBean.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users");
       ResultSet resultSet =  preparedStatement.executeQuery();
       while (resultSet.next()){
           System.out.println(resultSet.getString("email"));
       }


    }
}
