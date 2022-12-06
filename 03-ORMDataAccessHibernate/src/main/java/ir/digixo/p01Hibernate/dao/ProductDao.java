package ir.digixo.p01Hibernate.dao;

import ir.digixo.p01Hibernate.entities.Category;

import java.util.Collection;

public interface ProductDao {
    Collection loadProductsByCategory(Category category);

}
