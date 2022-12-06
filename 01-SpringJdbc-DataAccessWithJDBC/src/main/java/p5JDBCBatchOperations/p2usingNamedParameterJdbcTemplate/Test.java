package p5JDBCBatchOperations.p2usingNamedParameterJdbcTemplate;

import p1UsingJDBCTemplate.Employee;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        var context=new GenericXmlApplicationContext();
        context.load("file:C:\\Users\\Admin\\IdeaProjects\\spring\\5\\session8\\01-SpringJdbc-DataAccessWithJDBC\\src\\main\\java\\p5JDBCBatchOperations\\p2usingNamedParameterJdbcTemplate\\config.xml");
        context.refresh();
        final EmployeeDao batch = context.getBean("batch2", EmployeeDao.class);
        batch.batchUpdate(List.of(
                new Employee(4,"gg","gg","n","n"),
                new Employee(5,"ggg","ggg","nn","nn")
        ));
    }
}
