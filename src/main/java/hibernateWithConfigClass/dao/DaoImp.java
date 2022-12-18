package hibernateWithConfigClass.dao;



import hibernateWithConfigClass.entities.Product;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.Optional;

@Repository
public class DaoImp {
    private final SessionFactory sessionFactory ;

    public DaoImp(SessionFactory sessionFactory) {

        this.sessionFactory = sessionFactory;
    }

    public void add(Product product){
        sessionFactory.getCurrentSession().save(product);
    }
    @Transactional
    public Product findById(int id){
//        var hql = "from Product p where p.id = ?1";
//         var result =this.sessionFactory.getCurrentSession().createQuery(hql).setParameter(1,Long.valueOf(id)).list();
//         if(result.isEmpty() || result.size() == 0){
//             return null;
//         }
//         return (Product) result.get(0);
       Product res =  (Product) sessionFactory.getCurrentSession().get(Product.class,Long.valueOf(id));
       return res;
    }
    @Transactional
    public void delete (int id){
        String deleteQuery = "Delete from Product  p where p.id = ?1 ";
        Query query = sessionFactory.getCurrentSession().createQuery(deleteQuery).setParameter(1,Long.valueOf(id));
        query.executeUpdate();

    }
    public void deleteProduct(Product product)
    {
        sessionFactory.getCurrentSession().delete(product);
    }
    public void updateProduct(Product product)
    {
        sessionFactory.getCurrentSession().update(product);
    }
}
