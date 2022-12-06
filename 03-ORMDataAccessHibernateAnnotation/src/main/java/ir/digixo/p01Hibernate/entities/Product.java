package ir.digixo.p01Hibernate.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "product")
@Data
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /*@Column(name = "product_name")*/
    private String name;
    private int price;


    //one to many
    @ManyToOne(fetch = FetchType.LAZY,optional = false)//age true bashe va nullable paeein ro coment koni bedon category ham save mishe
   // @JoinColumn(name = "category_id",nullable = false)
    //uniderctional dont need @ManyToOne
   private Category category;


  /*  public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
*/
    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Product(Long id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product() {
    }

}
