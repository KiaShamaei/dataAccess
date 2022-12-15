package JDBCnameParameterJdbcTemplate;


import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class TestConfigClass {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Config.class);
        var dao = context.getBean(UserDao.class);
//        System.out.println(dao.findById(30));
        User user = new User(37, "67777999@gmail.com", "9666ima", "kiaBalapesar", "12346");
        var test  = dao.saveOrUpdate(user);
        System.out.println(test.toString());

    }
}
