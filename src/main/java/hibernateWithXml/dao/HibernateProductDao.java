package hibernateWithXml.dao;

import hibernateWithXml.entities.Category;
import hibernateWithXml.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public class HibernateProductDao implements ProductDao {
    public HibernateProductDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private final SessionFactory sessionFactory;
    public Collection loadProductsByCategory(Category category) {
        return this.sessionFactory.getCurrentSession()
                .createQuery("from Product product where product.category=?1")
                .setParameter(1, category)
                .list();
    }
   public void createProduct(Product product, Category category){
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
    public  void createCategory(Category category){
        sessionFactory.getCurrentSession().save(category);
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
