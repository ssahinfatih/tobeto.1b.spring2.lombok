package com.tobeto.spring.b;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoriesController {
    List<Category> inMemoryList=new ArrayList<>();

    @GetMapping
    public List<Category> get(){

        return inMemoryList;
    }

    @GetMapping("{id}")
    public Category getById(@PathVariable int id){
        Category category=inMemoryList.stream()
                .filter((p)->p.getId()==id)
                .findFirst()
                .orElseThrow();
        return category;

    }

    @PostMapping
    public Category add(@RequestBody Category category){
        inMemoryList.add(category);
        return category;
    }

    @PutMapping
    public boolean updateCategory(@RequestBody Category category){
        for(int i=0;i<inMemoryList.size();i++){
            Category category1=inMemoryList.get(i);
            if(category1.getId()==category.getId()){
                inMemoryList.set(i,category);
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