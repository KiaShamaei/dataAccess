package JDBCnameParameterJdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("UserDao")
public class UserDao {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    public UserDao(UserRowMapper userRowMapper) {
        this.userRowMapper = userRowMapper;
    }

    @Autowired
    public void setDataSource(@Qualifier("dataSource") DataSource dataSource) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    public List<User> findAll() {
        var sql = "select * from users";
        return jdbcTemplate.query(sql, userRowMapper);
    }

    public User findById(int id) {

        //1
        //SqlParameterSource namedParameters = new MapSqlParameterSource("id",id);
        //2
        //Map<String, Object> namedParameters = Collections.singletonMap("id", id);
        //3
//        Employee e=new Employee();
//        e.setId(id);
//        SqlParameterSource namedParameters = new BeanPropertySqlParameterSource(e);
        var sql = "select * from users where id = :id";
        SqlParameterSource s = new MapSqlParameterSource("id", 30);
        //or
        //SqlParameterSource s = new BeanPropertySqlParameterSource(new JDBCTemplateWithJdbcTemplate.User(35, "4545@gmail.com", "nima", "kiaBalapesar", "12346"));
        return jdbcTemplate.queryForObject(sql, s, userRowMapper);

    }

    public User saveOrUpdate(User user) {
        var sqlFind = " select *  from users where id = :id ";
        var sql = " insert into  users (first_name, last_name , email ,password) values(:first_name,:last_name,:email , :password)";
        var sqlUpdate = " UPDATE users  " +
                " SET first_name = :first_name ,  last_name = :last_name ,  email = :email ,  password = :password " +
                " WHERE id = :id ";
        SqlParameterSource s = new MapSqlParameterSource("id", user.getId());
        Map<String, String> mapParam = new HashMap<String, String>();
        var item = jdbcTemplate.queryForObject(sqlFind, s, userRowMapper);
        if (item == null) {

            mapParam.put("first_name", user.getFirst_name());
            mapParam.put("last_name", user.getLast_name());
            mapParam.put("email", user.getEmail());
            mapParam.put("password", user.getPassword());
            mapParam.put("id",String.valueOf(user.getId()));
            SqlParameterSource ss = new MapSqlParameterSource(mapParam);

            return jdbcTemplate.queryForObject(sql, ss, userRowMapper);

        } else {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            mapParam.put("first_name", user.getFirst_name());
            mapParam.put("last_name", user.getLast_name());
            mapParam.put("email", user.getEmail());
            mapParam.put("password", user.getPassword());
            mapParam.put("id",String.valueOf(user.getId()));
            SqlParameterSource ss = new MapSqlParameterSource(mapParam);
            SqlParameterSource sfind = new MapSqlParameterSource("id" , user.getId());
            jdbcTemplate.update(sqlUpdate,ss,keyHolder);
            return jdbcTemplate.queryForObject(sqlFind,sfind,userRowMapper);
        }
    }
//
//
//
//    public int[] updateBatchWithJdbcTemplate(List<User> users){
//        var sql = "update users set email= ? ,first_name = ?, last_name = ? , password= ? where id = ?";
//        return    jdbcTemplate.batchUpdate(sql,
//                new BatchPreparedStatementSetter() {
//            public void setValues(PreparedStatement ps, int i) throws
//                    SQLException {
//                User user = users.get(i);
//                ps.setString(1, user.getEmail());
//                ps.setString(2, user.getFirst_name());
//                ps.setString(3, user.getLast_name());
//                ps.setString(4, user.getPassword());
//                ps.setInt(5, user.getId());
//
//            }
//            public int getBatchSize() {
//                return users.size();
//            }
//        });
//    }


}
