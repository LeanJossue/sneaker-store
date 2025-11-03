package co.edu.poli.sneaker_store_back.repository;

import co.edu.poli.sneaker_store_back.entity.Sneaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SneakerRepository extends JpaRepository<Sneaker, Long> {
}
