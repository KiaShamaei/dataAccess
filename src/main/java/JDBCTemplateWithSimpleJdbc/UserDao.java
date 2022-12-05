package JDBCTemplateWithSimpleJdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository("UserDao")
public class UserDao {

    //in simpleJdbc we dont need row mapper

    private SimpleJdbcInsert jdbcTemplate ;


    @Autowired
    public void setDataSource(@Qualifier("dataSource") DataSource dataSource) {
        this.jdbcTemplate = new SimpleJdbcInsert(dataSource)
                .withTableName("users")
                .usingGeneratedKeyColumns("id")
                .usingColumns("first_name","last_name","email","password");
    }




    public int add() {
        Map<String, Object> parameters = new HashMap();
        // parameters.put("id", 10);
        parameters.put("first_name", "hasan");
        parameters.put("last_name", "hasani");
        parameters.put("username", "hasani");
        parameters.put("email", "123456");
        parameters.put("password", "123456");
       return jdbcTemplate.execute(parameters);
    }
    public void addRetrievingAutogeneratedKeys() {
        Map<String, Object> parameters = new HashMap();
        parameters.put("first_name", "hasan");
        parameters.put("last_name", "hasani");
        parameters.put("username", "hasani");
        parameters.put("password", "123456");
        Number newId = jdbcTemplate.executeAndReturnKey(parameters);
        System.out.println(newId);
    }
    public void addWithSqlParameterSource() {//dont insert first name
        SqlParameterSource parameters = new BeanPropertySqlParameterSource(new User(12,"dd","ddd","ddd","ddd"));
        Number newId = jdbcTemplate.executeAndReturnKey(parameters);
        System.out.println(newId);
    }


}