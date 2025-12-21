package kun.uz.repository;

import jakarta.transaction.Transactional;
import kun.uz.entities.ArticleLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ArticleLikeRepository extends JpaRepository<ArticleLikeEntity, String> {

    Optional<ArticleLikeEntity> findByIdAndVisibleIsTrue(String id);

    Optional<ArticleLikeEntity> findAllByVisibleIsTrue();
    @Modifying
    @Transactional
    @Query(value = "update ArticleLikeEntity set visible = false where id = ?1")
    void updateVisible(String id);
}