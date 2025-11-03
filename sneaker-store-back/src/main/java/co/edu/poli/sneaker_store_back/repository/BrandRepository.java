package co.edu.poli.sneaker_store_back.repository;

import co.edu.poli.sneaker_store_back.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
