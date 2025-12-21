package kun.uz.repository;

import jakarta.transaction.Transactional;
import kun.uz.entities.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, String> {
    Optional<CategoryEntity> findByNameUzAndVisibleIsTrue(String nameUz);

    Optional<CategoryEntity> findByNameEnAndVisibleIsTrue(String nameEn);

    Optional<CategoryEntity> findByNameRuAndVisibleIsTrue(String nameRu);

    Optional<CategoryEntity> findByIdAndVisibleIsTrue(String id);

    Optional<CategoryEntity> findAllByVisibleIsTrue();
    @Modifying
    @Transactional
    @Query(value = "update CategoryEntity set visible = false where id = ?1")
    void updateVisible(String id);
}