package p6SimpleJdbc.p02SimpleJdbcCall;

import p1UsingJDBCTemplate.Employee;

import java.util.List;

public interface Dao {
     int[] batchUpdate(final List<Employee> actors);
}
