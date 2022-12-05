package JDBCTemplateWithJdbcTemplate;

import dataSourceJdbcTemplate.classConfig.Config;
import dataSourceJdbcTemplate.classConfig.EmployDao;
import dataSourceJdbcTemplate.classConfig.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class TestConfigClass {
    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("classpath:config.xml");
        var dao = context.getBean(UserDao.class);

        //find all
//        List<User> users = dao.findAll();
//        users.stream().forEach(e-> System.out.println(e));

        //batch file
//        final UserDao batchDao = context.getBean("UserDao", UserDao.class);
//        batchDao.updateBatchWithJdbcTemplate(List.of(
//                new User(17,"aaaa","aa","aa","123"),
//                new User(18,"aa","aa","aa","123")
//        ));

        //findById
//        User user = dao.findById(20);
//        System.out.println(user);

        //update or save
        User user = new User(35, "4545@gmail.com", "nima", "kiaBalapesar", "12346");
       var item =  dao.saveOrUpdate(user);
        System.out.println(item);

    }
}
