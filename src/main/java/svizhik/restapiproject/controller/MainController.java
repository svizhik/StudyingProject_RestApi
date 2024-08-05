package svizhik.restapiproject.controller;

import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import svizhik.restapiproject.dto.Cat;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@RestController
public class MainController {

    @Autowired
    private ObjectMapper objectMapper;

    //List<Cat> cats = MainController.getCats;
    //
    //    List<Cat> cats2 = cats.stream()
    //            .filter(c -> c.getAge() > 7)
    //            .collect(Collectors.toList());
    //
    //        for(int i = 0; i < cats2.size(); i++){
    //        System.out.println(cats2.get(i));
    //    }

    List<Cat> cats = new ArrayList<>();

    public MainController(){
        cats.add(new Cat("без породы", 9, 6));
        cats.add(new Cat("сибирский", 3, 6));
        cats.add(new Cat("без породы", 9, 10));
        cats.add(new Cat("сибирский", 2, 7));
        cats.add(new Cat("без породы", 4, 4));
        cats.add(new Cat("британский вислоухий", 4, 6));
        cats.add(new Cat("сибирский", 3, 10));
        cats.add(new Cat("без породы", 7, 5));
        cats.add(new Cat("британский вислоухий", 3, 6));
        cats.add(new Cat("мейн-кун", 8, 10));
        cats.add(new Cat("сибирский", 2, 6));

        Stream<Cat> stream = cats.stream();
    }

    @GetMapping("api/cat")
    public String giveCat() {
        String jsonData = null;
        try{
            jsonData = objectMapper.writeValueAsString(cats);
        } catch (JsonProcessingException e) {
            System.out.println("Error with cat");
        }
        return jsonData;
    }

    @PostMapping("/api/special")
    public String giveSpecialCat(@RequestParam String name,
                                 @RequestParam int age,
                                 @RequestParam int weight) {
        Cat cat = new Cat(name, age, weight);
        String jsonData = null;
        try{
            jsonData = objectMapper.writeValueAsString(cat);
        } catch (JsonProcessingException e) {
            System.out.println("Error with cat");
        }
        return jsonData;
    }

    @PutMapping("/api/cat/update")
    public String updateCat(@RequestParam String name,
                            @RequestParam int age,
                            @RequestParam int weight) {
        for (Cat cat : cats) {
            if (cat.getName().equals(name)) {
                cat.setWeight(weight);
                cat.setAge(age);
                String jsonData = null;
                try {
                    jsonData = objectMapper.writeValueAsString(cat);
                } catch (JsonProcessingException e) {
                    System.out.println("Error with cat");
                }
                return jsonData;
            }
        }
        return "Cat not found";
    }


    @DeleteMapping("/api/cat/{age}")
    public String deleteCat(@PathVariable("age") int age) {
        for (Cat cat : cats) {
            if(cat.getAge() == age){
                String jsonData = null;
                try {
                    jsonData = objectMapper.writeValueAsString(cat);
                } catch (JsonProcessingException e) {
                    System.out.println("Error with cat");
                }
                cats.remove(cat);
                return jsonData;
            }
        }
        return "There are no cats these ages";
    }
}

