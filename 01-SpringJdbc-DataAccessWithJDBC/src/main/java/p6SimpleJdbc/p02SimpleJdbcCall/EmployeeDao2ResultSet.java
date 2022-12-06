package p6SimpleJdbc.p02SimpleJdbcCall;

import p1UsingJDBCTemplate.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository(value = "simple3")
public class EmployeeDao2ResultSet {

    private SimpleJdbcCall produreCall;

    @Autowired
    public void setDataSource(@Qualifier("dataSource1") DataSource dataSource)
    {
        JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);


        this.produreCall=new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("GetAllEmployees")
                .returningResultSet("employees",
                        BeanPropertyRowMapper.newInstance(Employee.class));;

    }
    public List getEmployeesList() {
        Map m = produreCall.execute(new HashMap<String, Object>(0));
        return (List) m.get("employees");
    }



}
