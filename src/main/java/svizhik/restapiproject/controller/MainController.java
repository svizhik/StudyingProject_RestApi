package svizhik.restapiproject.controller;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import svizhik.restapiproject.dto.Cat;
import svizhik.restapiproject.service.CatService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class MainController {

    @GetMapping("/api/cat")
    public List<Cat> giveCat(@RequestParam (required = false) Integer age) {
        return CatService.getCats(age);
    }

    @PostMapping("/api/cat")
    public ResponseEntity<?> addCat(@RequestBody Cat newCat) {
        CatService.addCat(newCat);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Cat added successfully");
        response.put("cat", newCat);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    //update
    @PutMapping("/api/cat")
    public ResponseEntity<?> updateCat(@RequestParam int id, @RequestBody Cat updatedCat) {
        boolean isUpdated = CatService.updateCat(id, updatedCat);
        Map<String, Object> response = new HashMap<>();
        if (isUpdated) {
            response.put("message", "Cat updated successfully");
            response.put("cat", updatedCat);
        }
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/api/cat")
    public ResponseEntity<?> deleteCat(@RequestParam Integer id) {
        int count = CatService.deleteCats(id);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Размер List<Cat> изменился на:");
        response.put("changedCount", count);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(200));
    }
}

