package dataSourceJdbcTemplate;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws SQLException {
        var context = new ClassPathXmlApplicationContext("file:D:\\SPRING\\dataAccess\\src\\main\\java\\dataSourceJdbcTemplate\\config.xml");
        JdbcTemplate template = context.getBean("jdbcTemplate" , JdbcTemplate.class);
//        Employee employee = template.queryForObject("select * from users where id = ?" ,(resultSet , rowNmu)->{
//            Employee newEmp = new Employee();
//            newEmp.setEmail(resultSet.getString("email"));
//            newEmp.setFirst_name(resultSet.getString("first_name"));
//            newEmp.setLast_name(resultSet.getString("last_name"));
//            newEmp.setPassword(resultSet.getString("password"));
//            return newEmp;
//        } , 18);
//        System.out.println(employee.getEmail());
        NamedParameterJdbcTemplate namedParameterJdbcTemplate =
                context.getBean("jdbcTemplate2" , NamedParameterJdbcTemplate.class);
        String sql = "select * from users";

        List<Employee> list = namedParameterJdbcTemplate.query(sql,(res, num)->{
            Employee employee = new Employee();
            employee.setEmail(res.getString("email"));
            employee.setPassword(res.getString("password"));
            return employee;
        });
        System.out.println(list.size());



    }
}
