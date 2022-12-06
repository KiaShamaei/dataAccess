package p2UsingNamedParameterJdbcTemplate;

import p1UsingJDBCTemplate.Employee;

import java.util.List;

public interface EmployeeDao {
    int countOfEmployeeByFirstName();
   // String findEmployeeById(int id);
   List<Employee> findEmployeeById(int id);
   List<Employee> findAll();
    //using rowMapper
  //  Employee findEmployeeById();

}
