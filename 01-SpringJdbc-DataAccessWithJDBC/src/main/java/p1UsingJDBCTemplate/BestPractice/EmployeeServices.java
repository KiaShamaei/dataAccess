package p1UsingJDBCTemplate.BestPractice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EmployeeServices {

    @Autowired
    private JdbcEmployeeDao employeeDao;



   @Transactional
    public void createEmployee() {
        employeeDao.createEmployee();
    }
}
