package com.nerzon_rest_begin.course.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nerzon_rest_begin.course.entity.Cat;
import com.nerzon_rest_begin.course.repository.CatRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MainController {

    //why this is here if you don't use it?
    private final ObjectMapper objectMapper;
    private final CatRepo catRepo;

    @PostMapping("/cats")
    public ResponseEntity<Cat> addCat(@RequestBody Cat cat) {
        Cat savedCat = catRepo.save(cat);
        return savedCat != null ? ResponseEntity.ok(savedCat) : ResponseEntity.notFound().build();
    }

    @SneakyThrows
    @GetMapping("/cats")
    public ResponseEntity<List<Cat>> allCats() {
        return ResponseEntity.ok(catRepo.findAll());
    }

    @GetMapping("/cats/{id}")
    public ResponseEntity<Cat> getCat(@PathVariable("id") int id) {
        Cat cat = catRepo.findById(id).orElse(null);
        return cat != null ? ResponseEntity.ok(cat) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/cats/{id}")
    public ResponseEntity<Boolean> deleteCat(@PathVariable("id") int id) {
        if (catRepo.existsById(id)){
            catRepo.deleteById(id);
            return ResponseEntity.ok(Boolean.TRUE);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/cats/{id}")
    public ResponseEntity<Cat> changeCat(@PathVariable("id") int id, @RequestBody Cat cat) {
        if (catRepo.existsById(id)) {
            cat.setId(id);
            log.info("New cat added: " + cat);
            return ResponseEntity.ok(catRepo.save(cat));
        }
        return ResponseEntity.notFound().build();
    }
}