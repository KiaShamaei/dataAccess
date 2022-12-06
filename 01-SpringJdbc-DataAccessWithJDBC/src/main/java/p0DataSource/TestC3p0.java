package p0DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestC3p0 {
    public static void main(String[] args) throws SQLException {
        var context=new ClassPathXmlApplicationContext("datasources.xml");
        // var context=new AnnotationConfigApplicationContext(DataSourceConfig.class);
        final DataSource myDataSource =(DataSource) context.getBean("dataSource3");
        final Connection connection = myDataSource.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("select * from product");
        final ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
            System.out.println(resultSet.getString("name"));
    }
}
