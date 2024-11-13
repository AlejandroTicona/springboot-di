package com.ale.springboot.di.app.springboot_di.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ale.springboot.di.app.springboot_di.models.Product;
import com.ale.springboot.di.app.springboot_di.repositories.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService{
    
    @Autowired
    //@Qualifier("productFoo")
    private IProductRepository repository;

    // public ProductServiceImpl(@Qualifier("productList")IProductRepository repository) {
    //     this.repository = repository;
    // }

    public List<Product> findAll() {
        return repository.findAll().stream().map(p ->{
            Double priceTax = p.getPrice() * 1.25d;
            //Product newProd = new Product(p.getId(),p.getName(),priceImp.longValue());
            Product newProd = (Product) p.clone();
            newProd.setPrice(priceTax.longValue());
            return newProd;
        }).collect(Collectors.toList());
    }
  
    public Product findById(Long id) {
        return repository.findById(id);
    }


}
