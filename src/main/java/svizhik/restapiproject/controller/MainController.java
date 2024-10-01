package svizhik.restapiproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import svizhik.restapiproject.service.CatService;
import svizhik.restapiproject.dto.Cat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;



@RestController
public class MainController {

    private final CatService catService;

    public MainController(CatService catService) {
        this.catService = catService;
    }

    @GetMapping("/api/cat/get")
    public List<Cat> getAllCats() {
        return catService.getAllCats();
    }

    @GetMapping("/api/cat/get/id")
    public List<Cat> getCatId(@RequestParam(required = false) Integer id) {
        return catService.getCatId(id);
    }

    @GetMapping("/api/cat/get/age")
    public List<Cat> getCat(@RequestParam(required = false) Integer age) {
        return catService.getCat(age);
    }

    @PostMapping("/api/cat/add")
    public ResponseEntity<?> addCat(@RequestBody Cat newCat) {
        catService.addCat(newCat);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cat added successfully");
        response.put("cat", newCat);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @PutMapping("/api/cat/update")
    public ResponseEntity<?> updateCat(@RequestBody Cat updatedCat) {
        boolean isUpdated = catService.updateCat(Optional.ofNullable(updatedCat));
        Map<String, Object> response = new HashMap<>();
        if (isUpdated) {
            response.put("message", "Cat updated successfully");
            response.put("cat", updatedCat);
        }
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/api/cat/delete")
    public ResponseEntity<?> deleteCat(@RequestParam Integer id) {
        boolean isDeleted = catService.deleteCat(id);
        Map<String, Object> response = new HashMap<>();
        if (isDeleted) {
            response.put("message", "The size of List<Cat> was changed.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "There is no Cat with such ID.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}
