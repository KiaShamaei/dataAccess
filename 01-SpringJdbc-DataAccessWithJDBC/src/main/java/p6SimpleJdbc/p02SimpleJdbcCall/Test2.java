package p6SimpleJdbc.p02SimpleJdbcCall;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        var context=new ClassPathXmlApplicationContext("datasources.xml");
        final EmployeeDao2ResultSet simple3 = context.getBean("simple3", EmployeeDao2ResultSet.class);
        final List employeesList = simple3.getEmployeesList();
        System.out.println(employeesList.size());
    }
}
