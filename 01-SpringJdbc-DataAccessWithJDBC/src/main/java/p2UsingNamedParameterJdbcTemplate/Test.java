package p2UsingNamedParameterJdbcTemplate;

import p1UsingJDBCTemplate.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class Test {
    private static Logger logger= LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        var context=new GenericXmlApplicationContext();
        context.load("file:C:\\Users\\Admin\\IdeaProjects\\spring\\5\\session8\\01-SpringJdbc-DataAccessWithJDBC\\src\\main\\java\\p2UsingNamedParameterJdbcTemplate\\config.xml");
        context.refresh();

        final JdbcEmployeeDao bean = context.getBean("dao2", JdbcEmployeeDao.class);
        final int i = bean.countOfEmployeeByFirstName();
        System.out.println("countOfEmployee is : "+i);

        final List<Employee> employeeList = bean.findEmployeeById(1);
        employeeList.forEach(System.out::println);
        System.out.println("@@@@@@@@@@");
        bean.findAll().forEach(x-> System.out.println(x.getFirstname()));
    }
}
