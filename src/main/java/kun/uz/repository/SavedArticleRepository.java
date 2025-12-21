package kun.uz.repository;

import jakarta.transaction.Transactional;
import kun.uz.entities.SavedArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SavedArticleRepository extends JpaRepository<SavedArticleEntity, String> {
    Optional<SavedArticleEntity> findByProfileIdAndVisibleIsTrue(String profileId);

    Optional<SavedArticleEntity> findByArticleIdAndVisibleIsTrue(String articleId);

    Optional<SavedArticleEntity> findByIdAndVisibleIsTrue(String id);

    List<SavedArticleEntity> findAllByVisibleIsTrue();
    @Modifying
    @Transactional
    @Query(value = "update SavedArticleEntity set visible = false where id = ?1")
    void updateVisible(String id);
    
}