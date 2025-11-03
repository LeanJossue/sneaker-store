package co.edu.poli.sneaker_store_back.service;

import co.edu.poli.sneaker_store_back.entity.Category;
import co.edu.poli.sneaker_store_back.entity.Sneaker;
import co.edu.poli.sneaker_store_back.repository.CategoryRepository;
import co.edu.poli.sneaker_store_back.repository.SneakerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> getAll() {
        return  repository.findAll();
    }

    @Transactional
    public Category create(Category category) {
        return repository.save(category);
    }

    @Transactional
    public Category update(Long id, Category category) {
        Category actual = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));

        actual.setName(category.getName());

        return repository.save(actual);
    }

    @Transactional
    public void delete (Long id) {
        repository.deleteById(id);
    }

}
