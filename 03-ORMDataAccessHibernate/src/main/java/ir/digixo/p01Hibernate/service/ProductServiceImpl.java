package ir.digixo.p01Hibernate.service;


import ir.digixo.p01Hibernate.entities.Category;
import ir.digixo.p01Hibernate.dao.HibernateProductDao;
import ir.digixo.p01Hibernate.entities.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ProductServiceImpl implements ProductService{
    private HibernateProductDao productDao;
    public void setProductDao(HibernateProductDao productDao) {
        this.productDao = productDao;
    }

    @Transactional(readOnly = true)
    public List<Product> findAllProducts(Category category) {
      return this.productDao.loadProductsByCategory(category).stream().toList();
    }

    @Transactional
    public void createProduct(Product product,Category category) {
       productDao.createProduct(product,category);
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
