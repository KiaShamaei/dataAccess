package p3RunningStatementsCreateTable;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        var context=new GenericXmlApplicationContext();
        context.load("file:C:\\Users\\Admin\\IdeaProjects\\spring\\5\\session8\\01-SpringJdbc-DataAccessWithJDBC\\src\\main\\java\\p3RunningStatementsCreateTable\\config.xml");
        context.refresh();
        final ExecuteAStatement bean = context.getBean(ExecuteAStatement.class);
        bean.doExecute();
    }
}
