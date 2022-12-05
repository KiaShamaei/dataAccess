package dataSourceConfigXml;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {
        var context = new ClassPathXmlApplicationContext("file:D:\\SPRING\\dataAccess\\src\\main\\java\\dataSourceConfigXml\\config.xml");
        DataSource  dataSource = context.getBean("dataSource3" , DataSource.class);
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select * from users");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString("email"));
        }
    }
}
