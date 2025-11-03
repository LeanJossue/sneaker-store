package co.edu.poli.sneaker_store_back.controller;

import co.edu.poli.sneaker_store_back.entity.Brand;
import co.edu.poli.sneaker_store_back.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
@CrossOrigin("")
public class BrandController {

    private final BrandService service;

    @GetMapping
    public List<Brand> getAll() {
        return service.getAll();
    }

    @PostMapping
    public Brand create(@RequestBody Brand brand) {
        return service.create(brand);
    }

    @PutMapping("/{id}")
    public Brand update(@PathVariable Long id, @RequestBody Brand brand) {
        return service.update(id, brand);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}