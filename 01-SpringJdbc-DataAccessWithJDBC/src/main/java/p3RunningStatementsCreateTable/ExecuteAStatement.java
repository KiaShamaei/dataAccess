package p3RunningStatementsCreateTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ExecuteAStatement {
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public void setDataSource(@Qualifier("dataSource2") DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void doExecute() {
        jdbcTemplate.execute("create table mytable (id integer, name varchar(100))");
    }
}
