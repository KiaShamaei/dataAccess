package p0DataSource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestBasicDataSource {
    public static void main(String[] args) throws SQLException {
       // var context=new ClassPathXmlApplicationContext("p07DependenciesandConfigurationinDetail.xml");
         var context=new AnnotationConfigApplicationContext(DataSourceConfig.class);
        final DataSource myDataSource = context.getBean("SimpleDriverDataSource", DataSource.class);
        final Connection connection = myDataSource.getConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("select * from product");
        final ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next())
            System.out.println(resultSet.getString("name"));
    }
}
