package ir.digixo.p01Hibernate;

import ir.digixo.p01Hibernate.config.Config;
import ir.digixo.p01Hibernate.entities.Category;
import ir.digixo.p01Hibernate.entities.Product;
import ir.digixo.p01Hibernate.service.ProductService;
import ir.digixo.p01Hibernate.service.ProductServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    private static Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {

        var context = new AnnotationConfigApplicationContext(Config.class);
        ProductService productService = context.getBean(ProductService.class);


        Category category = new Category();
        // category.setName("smart watch");
        category.setId(1l);
        //  productService.findAllProducts(category);

        //create
        Product product = new Product("BMW 2023", 60000);



      /*  // @ManyToOne(optional = true)//age true bashe va nullable paeein ro coment koni bedon category ham save mishe
           // @JoinColumn(name = "category_id"*//*,nullable = false*//*)
        productService.createProduct(product,null);*/

        productService.createProduct(product, category);

    }
}
