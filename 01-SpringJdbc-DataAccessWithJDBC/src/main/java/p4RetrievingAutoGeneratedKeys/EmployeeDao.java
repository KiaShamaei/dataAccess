package p4RetrievingAutoGeneratedKeys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import p1UsingJDBCTemplate.Employee;

import javax.sql.DataSource;
import java.sql.PreparedStatement;

//using named parameter notation
@Repository("dao3")
public class EmployeeDao  implements Dao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(@Qualifier("dataSource2") DataSource dataSource)
    {
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }


    @Override
    public Number CreateEmployee() {
        final String INSERT_SQL = "insert into Employee (first_name, last_name,username,password) values (?, ?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update((connection)->{
            PreparedStatement ps=connection.prepareStatement(INSERT_SQL,new String[]{"id"});
            ps.setString(1,"jafar2");
            ps.setString(2,"jafari2");
            ps.setString(3,"jef2");
            ps.setString(4,"123456");
            return ps;
        },keyHolder);

        return keyHolder.getKey();
    }
}