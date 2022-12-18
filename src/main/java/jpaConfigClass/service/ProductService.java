package jpaConfigClass.service;



import jpaConfigClass.dao.DaoImp;
import jpaConfigClass.entity.CategoryProduct;
import jpaConfigClass.entity.ProductJpa;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    private final DaoImp daoImp;

    public ProductService(DaoImp daoImp) {
        this.daoImp = daoImp;
    }
    public void addProduct(ProductJpa product ){
        daoImp.add(product);
    }
    @Transactional
    public void addCat(CategoryProduct categoryProduct){
        daoImp.addCat(categoryProduct);
    }
    public ProductJpa findProduct (Long id){
        return daoImp.find(id);
    }
    @Transactional
    public void deleteProduct(Long id){
        daoImp.delete(id);
    }

}
