package org.iesvdm.restapiecommercejpa.controller;

import org.iesvdm.restapiecommercejpa.model.Category;
import org.iesvdm.restapiecommercejpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Category create(@RequestBody Category category) {
        return categoryService.save(category);
    }

    @GetMapping
    public List<Category> getAll() {
        return categoryService.findAll();
    }
}
