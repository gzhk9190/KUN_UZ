package kun.uz.repository;

import jakarta.transaction.Transactional;
import kun.uz.entities.ArticleEntity;
import kun.uz.enums.ArticleStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface ArticleRepository extends JpaRepository<ArticleEntity, String> {

    @Modifying
    @Transactional
    @Query(value = "update ArticleEntity set status = ?2, publisherId = ?3 where id = ?1")
    int updateStatus(String id, ArticleStatus status, String publisherId);
}