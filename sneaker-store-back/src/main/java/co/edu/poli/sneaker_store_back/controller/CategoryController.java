package co.edu.poli.sneaker_store_back.controller;

import co.edu.poli.sneaker_store_back.entity.Category;
import co.edu.poli.sneaker_store_back.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@CrossOrigin("")
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public List<Category> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Category create(@RequestBody Category category) {
        return service.create(category);
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody  Category category) {
        return service.update(id, category);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}