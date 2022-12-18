package jpaConfigClass.entity;

import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class CategoryProduct implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category" ,cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ProductJpa> products;

    public CategoryProduct() {
    }

    public CategoryProduct(String name) {
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

    public Set<ProductJpa> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductJpa> products) {
        this.products = products;
    }
}
