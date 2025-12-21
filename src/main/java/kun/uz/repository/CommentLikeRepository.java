package kun.uz.repository;

import jakarta.transaction.Transactional;
import kun.uz.entities.CommentLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface CommentLikeRepository extends JpaRepository<CommentLikeEntity, String> {
    Optional<CommentLikeEntity> findByIdAndVisibleIsTrue(String id);

    Optional<CommentLikeEntity> findAllByVisibleIsTrue();
    @Modifying
    @Transactional
    @Query(value = "update CommentLikeEntity set visible = false where id = ?1")
    void updateVisible(String id);
}