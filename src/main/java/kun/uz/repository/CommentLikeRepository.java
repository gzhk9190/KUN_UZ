package kun.uz.repository;

import kun.uz.entities.CommentLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface CommentLikeRepository extends JpaRepository<CommentLikeEntity, String> {
}