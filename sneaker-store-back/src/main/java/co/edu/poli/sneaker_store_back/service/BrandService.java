package co.edu.poli.sneaker_store_back.service;

import co.edu.poli.sneaker_store_back.entity.Brand;
import co.edu.poli.sneaker_store_back.entity.Sneaker;
import co.edu.poli.sneaker_store_back.repository.BrandRepository;
import co.edu.poli.sneaker_store_back.repository.SneakerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository repository;

    public List<Brand> getAll() {
        return  repository.findAll();
    }

    @Transactional
    public Brand create(Brand brand) {
        return repository.save(brand);
    }

    @Transactional
    public Brand update(Long id, Brand brand) {
        Brand actual = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));

        actual.setName(brand.getName());

        return repository.save(actual);
    }

    @Transactional
    public void delete (Long id) {
        repository.deleteById(id);
    }
}
