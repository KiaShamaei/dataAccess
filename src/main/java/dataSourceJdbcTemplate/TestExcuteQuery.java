package dataSourceJdbcTemplate;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class TestExcuteQuery {
    public static void main(String[] args) {
        var context = new ClassPathXmlApplicationContext("file:D:\\SPRING\\dataAccess\\src\\main\\java\\dataSourceJdbcTemplate\\config.xml");
        JdbcTemplate template = context.getBean("jdbcTemplate" , JdbcTemplate.class);
        template.execute("create table gosht (id int  , raste varchar(100) )");
    }
}
