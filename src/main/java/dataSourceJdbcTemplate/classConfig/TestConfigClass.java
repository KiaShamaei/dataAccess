package dataSourceJdbcTemplate.classConfig;

import dataSourceJdbcTemplate.classConfig.Config;
import dataSourceJdbcTemplate.classConfig.EmployDao;
import dataSourceJdbcTemplate.classConfig.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class TestConfigClass {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Config.class);
        JdbcTemplate template = context.getBean("jdbcTemplate", JdbcTemplate.class);
        dataSourceJdbcTemplate.classConfig.Employee employee = template.queryForObject("select * from users where id = ?" ,(res, map)->{
            dataSourceJdbcTemplate.classConfig.Employee newEmp = new Employee();
            newEmp.setEmail(res.getString("email"));
            return newEmp;
        }, 18);
        Employee ali = new Employee();
        ali.setFirst_name("A");
        ali.setLast_name("AA");
        ali.setPassword("13245");
        ali.setEmail("a@k124.com");
        System.out.println(employee.getEmail());

        //rum with NamedParameter and dao class
        var dao = context.getBean( EmployDao.class);
        System.out.println(dao.findAll().size());
        //run with name MapSqlParameterSource and BeanPropertySqlParameterSource--> this get param as object
        System.out.println(dao.findById(19).get(0));

        System.out.println("this id "+ dao.creatUsers(ali));

    }
}
