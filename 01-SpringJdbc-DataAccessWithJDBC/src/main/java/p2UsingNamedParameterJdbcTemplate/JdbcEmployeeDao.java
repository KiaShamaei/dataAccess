package p2UsingNamedParameterJdbcTemplate;

import p1UsingJDBCTemplate.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

//using named parameter notation
@Repository("dao2")
public class JdbcEmployeeDao implements EmployeeDao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(@Qualifier("dataSource2") DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public int countOfEmployeeByFirstName() {
        String sql = "select count(*) from employee where first_name = :firstname";

        SqlParameterSource namedParameters = new MapSqlParameterSource("first_name", "rana");
        //or
        SqlParameterSource namedParameters2 = new BeanPropertySqlParameterSource(new Employee("rana", "rahnama", "rana.r", "123456"));


        return this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters2,
                Integer.class);

    }

    public int countOfEmployeeByFirstNameV2() {
        String sql = "select count(*) from employee where first_name = :first_name";

        Map<String, String> namedParameters = Collections.singletonMap("first_name", "rana");

        return this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                Integer.class);
    }

//   @Override
//    public String findEmployeeById(int id) {
//        String sql = "select first_name from employee where id = :id";
//        Map<String, Object> namedParameters = Collections.singletonMap("id", id);
//
//        //   SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(e);
//        return this.namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
//                String.class);
//    }

    @Override
    public List<Employee> findEmployeeById(int id) {
        String sql = "select * from employee where id = :id";
        //1
        //SqlParameterSource namedParameters = new MapSqlParameterSource("id",id);
        //2
        //Map<String, Object> namedParameters = Collections.singletonMap("id", id);
        //3
        Employee e=new Employee();
        e.setId(id);
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(e);

      return   namedParameterJdbcTemplate.query(sql,namedParameters,new EmployeeMapper());
    }

    @Override
    public List<Employee> findAll() {
        String sql = "select * from employee";
        return namedParameterJdbcTemplate.query(sql,new EmployeeMapper());
    }

    private static final class EmployeeMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setFirstname(rs.getString("first_name"));
            employee.setLastName(rs.getString("last_name"));
            employee.setUsername(rs.getString("username"));
            employee.setPassword(rs.getString("password"));
            return employee;
        }
    }
}
