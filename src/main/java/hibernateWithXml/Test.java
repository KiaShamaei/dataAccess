package hibernateWithXml;



import hibernateWithXml.entities.Category;
import hibernateWithXml.entities.Product;
import hibernateWithXml.service.ProductServiceImpl;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        var context=new ClassPathXmlApplicationContext("classpath:hibernateConfig.xml");
        final ProductServiceImpl bean = context.getBean(ProductServiceImpl.class);
        System.out.println("---------------------->");
        System.out.println(bean);
       Category category=new Category();
       category.setId(1l);
//        create one
        bean.createProduct(new Product("garmin",1000),category);
        bean.createProduct(new Product("garmin2",1000),category);
        bean.createProduct(new Product("garmin3",1000),category);
        bean.createCategory(category);





        final List<Product> allProducts = bean.findAllProducts(category);
        allProducts.forEach(x-> System.out.println(x.getName()));



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
