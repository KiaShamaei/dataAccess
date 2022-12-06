package p1UsingJDBCTemplate;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class C01Select
{
    public static void main( String[] args )
    {
        var context=new ClassPathXmlApplicationContext("config.xml");
        JdbcTemplate jdbcTemplate =(JdbcTemplate) context.getBean("jdbcTemplate");




     /*   int rowCount = jdbcTemplate.queryForObject("select count(*) from employee",
                Integer.class);
        System.out.println(rowCount);*/

      /*  int countOfEmployeeNamedReza = jdbcTemplate.queryForObject(
                "select count(*) from Employee where first_name = ?", Integer.class, "reza");
        System.out.println(countOfEmployeeNamedReza);

        String lastName = jdbcTemplate.queryForObject(
                "select last_name from Employee where id = ?",
                String.class, 1);
        System.out.println(lastName);*/

        ///////////////


       /* int id=1;
        Employee employee = jdbcTemplate.queryForObject(
                "select * from employee where id = ?",
                (resultSet, rowNum) -> {
                    Employee newEmployee = new Employee();
                    newEmployee.setId(resultSet.getInt("id"));
                    newEmployee.setFirstname(resultSet.getString("first_name"));
                    newEmployee.setLastName(resultSet.getString("last_name"));
                    newEmployee.setUsername(resultSet.getString("username"));
                    newEmployee.setPassword(resultSet.getString("password"));
                    return newEmployee;
                },
                id);

        System.out.println(employee.getFirstname());*/

        List<Employee> actors = jdbcTemplate.query("select * from Employee",
                (resultSet, rowNum) -> {
                    Employee employee1 = new Employee();
                    employee1.setId(resultSet.getInt("id"));
                    employee1.setFirstname(resultSet.getString("first_name"));
                    employee1.setLastName(resultSet.getString("last_name"));
                    employee1.setUsername(resultSet.getString("username"));
                    employee1.setPassword(resultSet.getString("password"));
                    return employee1;
                });
        System.out.println(actors.size());
        //amma behtare code haye bala ro be soret zir benevisim
        RowMapper<Employee> employeeRowMapper = (resultSet, rowNum) -> {
            Employee employee2 = new Employee();
            employee2.setFirstname(resultSet.getString("first_name"));
            employee2.setLastName(resultSet.getString("last_name"));
            return employee2;
        };
      List<Employee> employeeList=jdbcTemplate.query("select first_name, last_name from Employee",
                employeeRowMapper);

        //

        //Query for list
        final List<Map<String, Object>> mapList = jdbcTemplate.queryForList("select * from employee");
        System.out.println("map list is "+mapList);
    }

}
