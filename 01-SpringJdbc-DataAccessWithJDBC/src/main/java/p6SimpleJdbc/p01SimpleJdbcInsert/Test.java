package p6SimpleJdbc.p01SimpleJdbcInsert;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        var context=new GenericXmlApplicationContext();
        context.load("file:C:\\Users\\Admin\\IdeaProjects\\spring\\5\\session8\\01-SpringJdbc-DataAccessWithJDBC\\src\\main\\java\\p6SimpleJdbc\\p01SimpleJdbcInsert\\config.xml");
        context.refresh();
        final EmployeeDao bean = context.getBean("simple1",EmployeeDao.class);
        bean.addWithSqlParameterSource();
    }
}
