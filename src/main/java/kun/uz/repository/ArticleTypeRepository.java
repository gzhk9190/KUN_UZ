package kun.uz.repository;

import jakarta.transaction.Transactional;
import kun.uz.entities.ArticleTypeEntity;
import kun.uz.service.ArticleTypeService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ArticleTypeRepository extends JpaRepository<ArticleTypeEntity, String> {
    Optional<ArticleTypeEntity> findByNameUzAndVisibleIsTrue(String nameUz);

    Optional<ArticleTypeEntity> findByNameEnAndVisibleIsTrue(String nameEn);

    Optional<ArticleTypeEntity> findByNameRuAndVisibleIsTrue(String nameRu);

    Optional<ArticleTypeEntity> findByIdAndVisibleIsTrue(String id);

    Optional<ArticleTypeEntity> findAllByVisibleIsTrue();
    @Modifying
    @Transactional
    @Query(value = "update ArticleTypeEntity set visible = false where id = ?1")
    void updateVisible(String id);
}