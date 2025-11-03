package co.edu.poli.sneaker_store_back.repository;

import co.edu.poli.sneaker_store_back.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
