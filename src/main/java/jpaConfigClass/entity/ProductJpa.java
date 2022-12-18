package jpaConfigClass.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "ProductJpa")
public class ProductJpa implements Serializable {
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    /*@Column(name = "product_name")*/
    private String name;
    private int price;




    @ManyToOne()
   private CategoryProduct category;


    public CategoryProduct getCategory() {
        return category;
    }

    public void setCategory(CategoryProduct category) {
        this.category = category;
    }

    public ProductJpa(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public ProductJpa(long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ProductJpa() {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
