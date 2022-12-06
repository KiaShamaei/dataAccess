package p5JDBCBatchOperations.p2usingNamedParameterJdbcTemplate.p1usingjdbcTemplate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import p1UsingJDBCTemplate.Employee;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository("batch")
public class EmployeeDao  implements Dao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(@Qualifier("dataSource2") DataSource dataSource)
    {
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }


    @Override
    public int[] batchUpdate(List<Employee> employees) {
        return this.jdbcTemplate.batchUpdate(
                "update employee set first_name = ?, last_name = ? where id = ?",
                new BatchPreparedStatementSetter() {
                    public void setValues(PreparedStatement ps, int i) throws
                            SQLException {
                        Employee employee = employees.get(i);
                        ps.setString(1, employee.getFirstname());
                        ps.setString(2, employee.getLastName());
                        ps.setLong(3, employee.getId());
                    }
                    public int getBatchSize() {
                        return employees.size();
                    }
                });
    }
}
