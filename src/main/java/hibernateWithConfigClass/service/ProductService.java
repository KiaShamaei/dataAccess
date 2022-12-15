package hibernateWithConfigClass.service;

import hibernateWithConfigClass.dao.DaoImp;
import hibernateWithConfigClass.entities.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    private final DaoImp daoImp;

    public ProductService(DaoImp daoImp) {
        this.daoImp = daoImp;
    }

    @Transactional
    public void addProduct(Product product){
        this.daoImp.add(product);
    }
    public Product findById(int id){
        return this.daoImp.findById(id);
    }
    public void productDelete(int id){
        this.daoImp.delete(id);
    }
}
