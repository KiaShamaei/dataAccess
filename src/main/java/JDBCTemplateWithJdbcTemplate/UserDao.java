package JDBCTemplateWithJdbcTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository("UserDao")
public class UserDao {
    private JdbcTemplate jdbcTemplate;
    private final UserRowMapper userRowMapper;

    public UserDao(UserRowMapper userRowMapper) {
        this.userRowMapper = userRowMapper;
    }

    @Autowired
    public void setDataSource2(@Qualifier("dataSource") DataSource dataSource){
        this.jdbcTemplate= new JdbcTemplate(dataSource);
    }


    public List<User> findAll(){
        var sql = "select * from users";
       return jdbcTemplate.query(sql,userRowMapper);
    }
    public User findById(int id){
        var sql = "select * from users where id = ?";
        return jdbcTemplate.queryForObject(sql,userRowMapper,id);

    }
    public User saveOrUpdate(User user){
        var sqlFind = " select *  from users where id = ? ";
        var sql = " insert into  users (first_name, last_name , email ,password) values(?,?,?,?)";
        var sqlUpdate = " UPDATE users  " +
                " SET first_name = ? ,  last_name = ? ,  email = ? ,  password = ? " +
                " WHERE id = ? ";
        var item = jdbcTemplate.queryForObject(sqlFind,userRowMapper,user.getId());
        if(item == null){
            jdbcTemplate.update(sql,
                    user.getFirst_name(),
                    user.getLast_name(),
                    user.getEmail(),
                    user.getPassword()
            );
            return user;
        }else{
         jdbcTemplate.update(sqlUpdate,
                    user.getFirst_name(),
                    user.getLast_name(),
                    user.getEmail(),
                    user.getPassword(),
                    user.getId()
                    );
         return    jdbcTemplate.queryForObject(sqlFind,userRowMapper,user.getId());
        }
    }



    public int[] updateBatchWithJdbcTemplate(List<User> users){
        var sql = "update users set email= ? ,first_name = ?, last_name = ? , password= ? where id = ?";
        return    jdbcTemplate.batchUpdate(sql,
                new BatchPreparedStatementSetter() {
            public void setValues(PreparedStatement ps, int i) throws
                    SQLException {
                User user = users.get(i);
                ps.setString(1, user.getEmail());
                ps.setString(2, user.getFirst_name());
                ps.setString(3, user.getLast_name());
                ps.setString(4, user.getPassword());
                ps.setInt(5, user.getId());

            }
            public int getBatchSize() {
                return users.size();
            }
        });
    }




}
