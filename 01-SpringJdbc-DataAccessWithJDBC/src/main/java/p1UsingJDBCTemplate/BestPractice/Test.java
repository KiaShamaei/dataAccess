package p1UsingJDBCTemplate.BestPractice;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import p1UsingJDBCTemplate.Employee;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        var context=new ClassPathXmlApplicationContext("config.xml");
        JdbcEmployeeDao employeeDao = context.getBean(JdbcEmployeeDao.class);
        //create
      // employeeDao.createEmployee();
        //findByid
       /* Employee employeeById = employeeDao.findEmployeeById(2);
        System.out.println(employeeById.getFirstname());*/

        //findAll
        List<Employee> all = employeeDao.findAll();
        all.forEach(employee -> {
            System.out.println(employee.getFirstname());
        });

    }
}
