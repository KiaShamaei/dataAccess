package p1UsingJDBCTemplate.BestPractice;

import p1UsingJDBCTemplate.Employee;

import java.util.List;

public interface EmployeeDao {
    void createEmployee();
     Employee findEmployeeById(int id);
     List<Employee> findAll();
}
