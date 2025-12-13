package kun.uz.repository;

import kun.uz.entities.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface CommentRepository extends JpaRepository<CommentEntity, String> {
}