package p6SimpleJdbc.p02SimpleJdbcCall;

import p1UsingJDBCTemplate.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository("batch2")
public class EmployeeDao  implements Dao {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;;

    @Autowired
    public void setDataSource(@Qualifier("dataSource2") DataSource dataSource)
    {
        this.namedParameterJdbcTemplate=new NamedParameterJdbcTemplate(dataSource);
    }


    @Override
    public int[] batchUpdate(List<Employee> employees) {
        return this.namedParameterJdbcTemplate.batchUpdate(
                "update employee set first_name = :firstname, last_name = :lastName where id = :id",
        SqlParameterSourceUtils.createBatch(employees));

    }
}
