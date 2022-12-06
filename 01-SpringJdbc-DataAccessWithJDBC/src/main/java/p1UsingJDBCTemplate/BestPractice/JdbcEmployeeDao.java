package p1UsingJDBCTemplate.BestPractice;

import p1UsingJDBCTemplate.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
@Slf4j
public class JdbcEmployeeDao implements EmployeeDao {

   // private static Logger logger= LoggerFactory.getLogger(JdbcEmployeeDao.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(@Qualifier("dataSource2") DataSource dataSource)
    {
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }

    @Override
    public void createEmployee() {
        log.info("in create Employee");
        this.jdbcTemplate.update(
                "insert into Employee (first_name, last_name,username,password) values (?, ?,?,?)",
                "mahsa", "jadidi", "mahsa123", "33123456111");
    }

    //old
    /*@Override
    public Employee findEmployeeById(int id) {
        jdbcTemplate.queryForObject("select * from employee where  id=?",new Object[]{id},Employee.class);
        return null;
    }*/
    //new
    @Override
    public Employee findEmployeeById(int id) {
        Employee employee = jdbcTemplate.queryForObject(
                "select * from employee where id = ?",
                (resultSet, rowNum) -> {
                    Employee newEmployee = new Employee();
                    newEmployee.setId(resultSet.getInt("id"));
                    newEmployee.setFirstname(resultSet.getString("first_name"));
                    newEmployee.setLastName(resultSet.getString("last_name"));
                    newEmployee.setUsername(resultSet.getString("username"));
                    newEmployee.setPassword(resultSet.getString("password"));
                    return newEmployee;
                },
                id);
        return employee;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = jdbcTemplate.query(
                "select * from Employee",
                (resultSet, rowNum) -> {
                    Employee employee1 = new Employee();
                    employee1.setId(resultSet.getInt("id"));
                    employee1.setFirstname(resultSet.getString("first_name"));
                    employee1.setLastName(resultSet.getString("last_name"));
                    employee1.setUsername(resultSet.getString("username"));
                    employee1.setPassword(resultSet.getString("password"));
                    return employee1;
                });

        return employees;
    }

}
