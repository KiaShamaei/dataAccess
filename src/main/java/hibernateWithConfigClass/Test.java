package hibernateWithConfigClass;

import hibernateWithConfigClass.config.Config;
import hibernateWithConfigClass.dao.DaoImp;
import hibernateWithConfigClass.entities.Product;
import hibernateWithConfigClass.service.ProductService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Test {
    public static void main(String[] args) {
        var conntext = new AnnotationConfigApplicationContext(Config.class);
        var service = conntext.getBean(ProductService.class);
//        Product product = new Product();
//        product.setName("ali3");
//        product.setPrice(2000);
//        service.addProduct(product);
        System.out.println("______________>");
        System.out.println(service.findById(3));
        service.productDelete(1);


    }
}
