package kun.uz.repository;

import kun.uz.entities.SavedArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface SavedArticleRepository extends JpaRepository<SavedArticleEntity, String> {
}