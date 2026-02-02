package kun.uz.repository;

import kun.uz.entities.ArticleArticleTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface ArticleArticleTypeRepository extends JpaRepository<ArticleArticleTypeEntity, String> {

    List<ArticleArticleTypeEntity> getAllByArticleTypeId(String articleTypeId);
}