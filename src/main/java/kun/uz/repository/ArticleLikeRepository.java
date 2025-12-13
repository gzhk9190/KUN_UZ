package kun.uz.repository;

import kun.uz.entities.ArticleLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface ArticleLikeRepository extends JpaRepository<ArticleLikeEntity, String> {

}