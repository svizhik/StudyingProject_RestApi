package svizhik.restapiproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/api/cat")
    public List<Cat> getCat(@RequestParam(required = false) Integer age) {
        return catService.getCat(age);
    }

    @PostMapping("/api/cat")
    public ResponseEntity<?> addCat(@RequestBody Cat newCat) {
        catService.addCat(newCat);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cat added successfully");
        response.put("cat", newCat);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @PutMapping("/api/cat")
    public ResponseEntity<?> updateCat(@RequestBody Cat updatedCat) {
        boolean isUpdated = catService.updateCat(Optional.ofNullable(updatedCat));
        Map<String, Object> response = new HashMap<>();
        if (isUpdated) {
            response.put("message", "Cat updated successfully");
            response.put("cat", updatedCat);
        }
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/api/cat")
    public ResponseEntity<?> deleteCat(@RequestParam Integer id) {
        int count = catService.deleteCat(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Размер List<Cat> изменился на:");
        response.put("changedCount", count);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

}
