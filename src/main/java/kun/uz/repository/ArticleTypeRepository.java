package kun.uz.repository;

import kun.uz.entities.ArticleTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface ArticleTypeRepository extends JpaRepository<ArticleTypeEntity, String> {
}