package ir.digixo.p01Hibernate.dao;

import ir.digixo.p01Hibernate.entities.Category;
import ir.digixo.p01Hibernate.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class HibernateProductDao implements ProductDao {
    private SessionFactory sessionFactory;
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public Collection loadProductsByCategory(Category category) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("from Product product where product.category=?1")
                .setParameter(1, category)
                .list();
    }
   public void createProduct(Product product,Category category){
      /*  Set<Product> productSet=new HashSet<>();
        productSet.add(product);

        Category category=new Category("samrt Phone");
        category.setProducts(productSet);


        // productDao.create(category);*/
        product.setCategory(category);
        Session session = this.sessionFactory.getCurrentSession();
       // session.beginTransaction();
        session.save(product);
       // session.getTransaction().commit();
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
