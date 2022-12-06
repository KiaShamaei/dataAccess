package ir.digixo.p01Hibernate.dao;

import ir.digixo.p01Hibernate.entities.Category;
import ir.digixo.p01Hibernate.entities.Product;

import java.util.Collection;

public interface ProductDao {
    Collection loadProductsByCategory(Category category);

    void createProduct(Product product, Category category);

    void deleteProduct(Product product);

    void updateProduct(Product product);
}
