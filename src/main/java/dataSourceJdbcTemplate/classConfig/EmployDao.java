package dataSourceJdbcTemplate.classConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository("EmployDao")
public class EmployDao {
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(@Qualifier("dataSource") DataSource dataSource) {
        this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    @Autowired
    public void setDataSource2(@Qualifier("dataSource") DataSource dataSource){
        this.jdbcTemplate= new JdbcTemplate(dataSource);
    }


    public List<Employee> findAll(){
        var sql = "select * from users";
        return namedParameterJdbcTemplate.query(sql,(res, no)->{
            Employee newEmp = new Employee();
            newEmp.setEmail(res.getString("email"));
            newEmp.setFirst_name(res.getString("first_name"));
            return newEmp;
        });
    }
    public List<Employee> findById(int id){
        var sql = "select * from users where id = :id";
        //1
        //SqlParameterSource namedParameters = new MapSqlParameterSource("id",id);
        //2
        //Map<String, Object> namedParameters = Collections.singletonMap("id", id);
        //3
        Employee e=new Employee();
        e.setId(id);
        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(e);

        return namedParameterJdbcTemplate.query(sql,namedParameters,(res,no)->{
            Employee newEmp = new Employee();
            newEmp.setFirst_name(res.getString("first_name"));
            newEmp.setLast_name(res.getString("last_name"));
            newEmp.setEmail(res.getString("email"));
            newEmp.setPassword(res.getString("password"));
            return newEmp;
        });
    }
    public Number creatUsers(Employee e){
        var sql = "insert into users(first_name, last_name , email , password) value(?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((con)->{
            PreparedStatement ps = con.prepareStatement(sql,new String[]{"id"});
            ps.setString(1, e.getFirst_name());
            ps.setString(2, e.getLast_name());
            ps.setString(3, e.getEmail());
            ps.setString(4, e.getPassword());
            return ps;
        },keyHolder);
       return keyHolder.getKey();
    }

}
