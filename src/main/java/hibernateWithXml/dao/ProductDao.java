package hibernateWithXml.dao;

import hibernateWithXml.entities.Category;

import java.util.Collection;

public interface ProductDao {
    Collection loadProductsByCategory(Category category);

}
