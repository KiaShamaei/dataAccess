package ir.digixo.p01Hibernate.entities;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
@Entity(name = "category")
@Table(name = "category")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    //one category has many product
    //bidirectional
   // @OneToMany(mappedBy = "category"/*,fetch = FetchType.LAZY*/,orphanRemoval = true,cascade = CascadeType.ALL)
    //@Cascade(org.hibernate.annotations.CascadeType.ALL)


    //unidirectional
   @OneToMany(
           cascade = CascadeType.ALL,
           orphanRemoval = true
   )
    private Set<Product> products;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
