package ir.digixo.p01Hibernate.entities;

import java.io.Serializable;
import java.util.Set;

public class Category implements Serializable {


    private Long id;
    private String name;
    private Set<Product> products;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
