package com.tobeto.spring.b;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductsController {
    List<Product> inMemoryList=new ArrayList<>();
    @GetMapping
    public List<Product> get(){
        return inMemoryList;
    }

    @GetMapping("{id}")
    public Product getById(@PathVariable int id){
        Product product=inMemoryList.stream()
                .filter((p)->p.getId()==id)
                .findFirst()
                .orElseThrow();
        return product;
    }

    @PostMapping
    public Product add(@RequestBody Product product){
        inMemoryList.add(product);
        return product;
    }

    @PutMapping
    public  boolean updateProduct(@RequestBody Product product){
        for(int i=0;i<inMemoryList.size();i++){
            Product product1=inMemoryList.get(i);
            if(product1.getId()==product.getId()){
                inMemoryList.set(i,product);
                return true;
            }

        }
        return false;
    }

    @DeleteMapping("{id}")
    public boolean delete(@PathVariable int id){
        boolean delete=inMemoryList.remove(inMemoryList.stream()
                .filter((p)->p.getId()==id)
                .findFirst()
                .orElseThrow());
        return delete;
    }
}