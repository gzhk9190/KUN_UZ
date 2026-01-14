package kun.uz.repository;

import jakarta.transaction.Transactional;
import kun.uz.entities.ArticleTypeEntity;
import kun.uz.entities.CommentEntity;
import kun.uz.entities.ProfileEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<CommentEntity, String> {
    Optional<CommentEntity> findByIdAndVisibleIsTrue(String id);

    Optional<CommentEntity> findAllByVisibleIsTrue();
    @Modifying
    @Transactional
    @Query(value = "update CommentEntity set visible = false where id = ?1")
    void updateVisible(String id);
    Page<CommentEntity> findByVisible(Boolean visible, Pageable pageable);

    List<CommentEntity> findAllByVisibleIsTrueAndArticleId(String articleId);

    List<CommentEntity> getAllByReplyId(String commentId);
}