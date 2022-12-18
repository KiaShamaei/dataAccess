package jpaConfigClass.dao;





import jpaConfigClass.entity.CategoryProduct;
import jpaConfigClass.entity.ProductJpa;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Repository
public class DaoImp {
    @PersistenceContext
    private  EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
@Transactional
    public void add(ProductJpa product){

        entityManager.persist(product);
    }

    public void delete(Long id){
        var res = entityManager.find(ProductJpa.class,id);
        entityManager.remove(res);

    }
    public ProductJpa find(Long id){
        return  entityManager.find(ProductJpa.class , id);
    }
    @Transactional
    public void addCat (CategoryProduct categoryProduct){
         entityManager.persist(categoryProduct);
    }
}
