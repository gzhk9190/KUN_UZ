package kun.uz.repository;

import kun.uz.entities.SavedArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SavedArticleRepository extends JpaRepository<SavedArticleEntity, String> {
    Optional<SavedArticleEntity> findByProfileIdAndVisibleIsTrue(String profileId);

    Optional<SavedArticleEntity> findByArticleIdAndVisibleIsTrue(String articleId);
}