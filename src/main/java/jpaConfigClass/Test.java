package jpaConfigClass;

import jpaConfigClass.config.Config;
import jpaConfigClass.entity.CategoryProduct;
import jpaConfigClass.entity.ProductJpa;
import jpaConfigClass.service.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(Config.class);
        var service = context.getBean(ProductService.class);

        Set<ProductJpa> set = new HashSet<ProductJpa>();
        CategoryProduct categoryProduct = new CategoryProduct();
        categoryProduct.setName("oimage");
        ProductJpa product = new ProductJpa();
        product.setName("siaba");
        product.setPrice(30000);
        product.setCategory(categoryProduct);

        categoryProduct.setProducts(set);
        service.addCat(categoryProduct);
        service.addProduct(product);


//        service.addProduct(product);
//        service.deleteProduct(1l);

//        System.out.println( service.findProduct(2l));;

    }
}
