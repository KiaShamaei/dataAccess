package p5JDBCBatchOperations.p2usingNamedParameterJdbcTemplate.p1usingjdbcTemplate;


import org.springframework.context.support.GenericXmlApplicationContext;
import p1UsingJDBCTemplate.Employee;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        var context=new GenericXmlApplicationContext();
        context.load("file:C:\\Users\\Admin\\IdeaProjects\\spring\\5\\session8\\01-SpringJdbc-DataAccessWithJDBC\\src\\main\\java\\p5JDBCBatchOperations\\p1usingjdbcTemplate\\config.xml");
        context.refresh();
        final EmployeeDao batch = context.getBean("batch", EmployeeDao.class);
        batch.batchUpdate(List.of(
                new Employee(4,"zzzz","zzz","zz","zzz"),
                new Employee(5,"zzz","zzz","zz","zzz")
        ));
    }
}
