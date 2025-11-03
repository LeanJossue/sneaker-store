package co.edu.poli.sneaker_store_back.service;

import co.edu.poli.sneaker_store_back.entity.Sneaker;
import co.edu.poli.sneaker_store_back.repository.SneakerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SneakerService {

    private final SneakerRepository repository;

    public List<Sneaker> getAll() {
        return  repository.findAll();
    }

    @Transactional
    public Sneaker create(Sneaker sneaker) {
        return repository.save(sneaker);
    }

    @Transactional
    public Sneaker update(Long id, Sneaker sneaker) {
        Sneaker actual = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found"));

       actual.setName(sneaker.getName());
       actual.setBrand(sneaker.getBrand());
       actual.setColor(sneaker.getColor());
       actual.setSize(sneaker.getSize());
       actual.setStock(sneaker.getStock());
       actual.setPrice(sneaker.getPrice());

       return repository.save(actual);
    }

    @Transactional
    public void delete (Long id) {
        repository.deleteById(id);
    }

}
