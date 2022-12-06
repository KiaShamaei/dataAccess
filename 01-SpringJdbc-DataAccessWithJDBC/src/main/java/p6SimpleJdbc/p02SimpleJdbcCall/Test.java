package p6SimpleJdbc.p02SimpleJdbcCall;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        var context=new ClassPathXmlApplicationContext("p08p02.xml");

        final EmployeeDao simple2 = context.getBean("simple2", EmployeeDao.class);


    }
}
