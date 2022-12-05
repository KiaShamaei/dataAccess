package JDBCTemplateWithSimpleJdbc;


import JDBCnameParameterJdbcTemplate.Config;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class TestConfigClass {
    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("file:D:\\SPRING\\dataAccess\\src\\main\\java\\JDBCTemplateWithSimpleJdbc\\configSimpleJdbc.xml");
        var dao = context.getBean(UserDao.class);
//        System.out.println(dao.findById(30));
        System.out.println(dao.add());
    }
}
