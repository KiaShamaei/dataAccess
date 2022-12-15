package hibernateWithXml.service;


import hibernateWithXml.dao.HibernateProductDao;
import hibernateWithXml.entities.Category;
import hibernateWithXml.entities.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    public ProductServiceImpl(HibernateProductDao productDao) {
        this.productDao = productDao;
    }

    private final HibernateProductDao productDao;


    @Transactional(readOnly = true)
    public List<Product> findAllProducts(Category category) {
      return this.productDao.loadProductsByCategory(category).stream().toList();
    }

    @Transactional
    public void createProduct(Product product,Category category) {
       productDao.createProduct(product,category);
    }

    @Transactional
    public void createCategory(Category category) {
        productDao.createCategory(category);
    }
    @Transactional
    public void deleteProduct(Product product) {
        productDao.deleteProduct(product);
    }

    @Transactional
    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }
}
