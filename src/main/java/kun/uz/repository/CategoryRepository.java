package kun.uz.repository;

import kun.uz.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
}