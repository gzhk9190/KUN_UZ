package kun.uz.repository;

import kun.uz.entities.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface ArticleRepository extends JpaRepository<ArticleEntity, String> {
}