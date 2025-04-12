package com.anyue.admin.controller;

import com.anyue.common.entity.Category;
import com.anyue.admin.service.CategoryService;
import com.anyue.common.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public Result getCategories(@RequestParam(required = false) Integer parentId) {
        return categoryService.getCategories(parentId);
    }

    @GetMapping("/categories/{id}")
    public Result getCategory(@PathVariable("id") Integer id) {
        return categoryService.getCategory(id);
    }

    @PostMapping("/categories")
    public Result addCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @PutMapping("/categories")
    public Result updateCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }

}
