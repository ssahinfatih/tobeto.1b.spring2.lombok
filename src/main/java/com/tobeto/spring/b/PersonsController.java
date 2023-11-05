package com.tobeto.spring.b;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/persons")
public class PersonsController {
    List<Person> inMemoryList=new ArrayList<>();

    @GetMapping
    public List<Person> get(){

        return inMemoryList;
    }


    //@GetMapping("getById")

    //Query String , Path veriables
    //Path => http://localhost:8080/api/persons/1/asli/21  --->  1=id
    //Query String => ttp://localhost:8080/api/persons/getById?id=10&name=asl&age=22

    //public int getById(@RequestParam int id){
    //  return id;
    //}
    @GetMapping("{id}")     // {} id dinamik olarak  değişir
    public Person getById(@PathVariable int id){
        //Lambda Expression
        //Stream API
        //Optional<Person> person=inMemoryList.stream()
        Person person=inMemoryList.stream()      //Lambda Expression
                .filter((p) -> p.getId()==id)
                .findFirst().orElseThrow();
        return person;
    }
    @PostMapping
    public Person add(@RequestBody Person person){
        // Person person=new Person(1,"","",21);
        inMemoryList.add(person);
        return person;
    }

    @PutMapping
    //public String  update(@RequestBody Person uperson){
    public boolean updatePerson( @RequestBody Person person){
        for (int i = 0; i < inMemoryList.size(); i++){
            Person person1 = inMemoryList.get(i);
            if(person1.getId() == person.getId())
            {
                inMemoryList.set(i, person);

                return true;
            }
        }
        return false;

    }


    @DeleteMapping({"{id}"})
    public boolean delete(@PathVariable int id){
        boolean delete=inMemoryList.remove(inMemoryList
                .stream()
                .filter((p) -> p.getId()==id)
                .findFirst()
                .orElseThrow());

        return delete;

    }

}