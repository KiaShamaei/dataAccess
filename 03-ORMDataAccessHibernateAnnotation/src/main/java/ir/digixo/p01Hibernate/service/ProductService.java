package ir.digixo.p01Hibernate.service;

import ir.digixo.p01Hibernate.entities.Category;
import ir.digixo.p01Hibernate.entities.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAllProducts(Category category);

    void createProduct(Product product, Category category);

    void deleteProduct(Product product);

    void updateProduct(Product product);
}
