package com.nerzon_rest_begin.course.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nerzon_rest_begin.course.entity.Cat;
import com.nerzon_rest_begin.course.repository.CatRepo;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    @Autowired
    private final ObjectMapper objectMapper;

    @Autowired
    private final CatRepo catRepo;

    @PostMapping("/api/add")
    public void addCat(@RequestBody Cat cat) {
        System.out.println("New row: " + catRepo.save(cat));
    }

    @SneakyThrows
    @GetMapping("/api/all")
    public List<Cat> allCats() {
        return catRepo.findAll();
    }

    @GetMapping("/api")
    public Cat getCat(@RequestParam int id) {
        return catRepo.findById(id).orElseThrow();
    }

    @DeleteMapping("/api")
    public void deleteCat(@RequestParam int id) {
        catRepo.deleteById(id);
    }

    @PutMapping("/api/add")
    public String changeCat(@RequestBody Cat cat) {
        if (!catRepo.existsById(cat.getId())) {
            return "No such now";
        }
        log.info("New row: " + catRepo.save(cat));
        return cat.toString();
    }
}