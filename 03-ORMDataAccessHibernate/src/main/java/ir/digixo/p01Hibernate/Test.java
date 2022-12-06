package ir.digixo.p01Hibernate;


import ir.digixo.p01Hibernate.entities.Category;
import ir.digixo.p01Hibernate.entities.Product;
import ir.digixo.p01Hibernate.service.ProductServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        var context=new ClassPathXmlApplicationContext("config.xml");
        final ProductServiceImpl bean = context.getBean(ProductServiceImpl.class);
       Category category=new Category();
       category.setId(2l);

      /*  final List<Product> allProducts = bean.findAllProducts(category);
        allProducts.forEach(x-> System.out.println(x.getName()));*/

        //create
        bean.createProduct(new Product("garmin",1000),category);

        //delete
        /*Product product=new Product();
        product.setId(51);
        bean.deleteProduct(product);*/

        //update
      /*  Product product=new Product();
        product.setId(50);
        product.setName("garmin");
        product.setPrice(2000);
        product.setCategory(category);
        bean.updateProduct(product);*/
    }


}
