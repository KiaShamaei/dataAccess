package ir.digixo;

import static org.junit.Assert.assertTrue;

import ir.digixo.p01Hibernate.entities.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Autowired
    private SessionFactory sessionFactory;
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {

    }
}
