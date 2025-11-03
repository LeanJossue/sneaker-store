package co.edu.poli.sneaker_store_back.controller;

import co.edu.poli.sneaker_store_back.entity.Sneaker;
import co.edu.poli.sneaker_store_back.service.SneakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sneakers")
@RequiredArgsConstructor
@CrossOrigin("")
public class SneakerController {

    private final SneakerService service;

    @GetMapping
    public List<Sneaker> all() {
        return  service.getAll();
    }

    @PostMapping
    public Sneaker create(@RequestBody Sneaker sneaker) {
        return service.create(sneaker);
    }

    @PutMapping("/{id}")
    public Sneaker update(@PathVariable Long id, @RequestBody Sneaker sneaker) {
        return service.update(id, sneaker);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
