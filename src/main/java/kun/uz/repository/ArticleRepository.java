package kun.uz.repository;

import jakarta.transaction.Transactional;
import kun.uz.dto.request.ArticleTypeRequestDTO;
import kun.uz.entities.ArticleEntity;
import kun.uz.entities.ProfileEntity;
import kun.uz.enums.ArticleStatus;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<ArticleEntity, String> {

    @Modifying
    @Transactional
    @Query(value = "update ArticleEntity set status = ?2, publisherId = ?3 where id = ?1")
    int updateStatus(String id, ArticleStatus status, String publisherId);


    @Transactional
    @Modifying
    @Query("from ArticleEntity where id not in :excludedids ORDER BY createDate desc limit 8")
    List<ArticleEntity> getAllWhichAreNot(String[] ids);


    ArticleEntity getByIdAndVisibleIsTrue(String id, Boolean visible);

    @Transactional
    @Modifying
    @Query("from ArticleEntity,ArticleArticleTypeEntity where id != ?2 and id in (select articleId from ArticleArticleTypeEntity where articleTypeId = ?1 ) ORDER BY createDate desc")
    List<ArticleEntity> findTop4ByTypeExceptId(String typeId, String exceptId, PageRequest of);

    @Transactional
    @Modifying
    @Query("from ArticleEntity order by viewCount desc")
    List<ArticleEntity> mostread4();

    @Transactional
    @Modifying
    @Query("from ArticleEntity as a, ArticleArticleTypeEntity as e  where a.regionId = ?1 and a.id in (select articleId from ArticleArticleTypeEntity where e = ?2) order by a.createDate desc ")
    List<ArticleEntity> last5ByRegionKeyAndType(String regionkey, ArticleTypeRequestDTO articleTypeRequestDTO, PageRequest of);


    @Transactional
    @Modifying
    @Query("from ArticleEntity as a where a.categoryId =?1 and a.visible is true order by a.createDate desc ")
    List<ArticleEntity> last5ByCategoryId(String categorykey, PageRequest of);


    @Transactional
    @Modifying
    @Query("update ArticleEntity as a  set a.viewCount = a.viewCount+1 where a.id=?1")
    Object incViewCount(String articleId);

    @Transactional
    @Modifying
    @Query("update ArticleEntity as a  set a.sharedCount = a.sharedCount+1 where a.id=?1")
    Object incShareCount(String articleId);

    @Query("SELECT a FROM ArticleEntity a " +
            "JOIN ArticleTypeEntity t " +
            "WHERE t.nameUz = :nameUz AND a.status = 'PUBLISHED' " +
            "ORDER BY a.createDate DESC")
    List<ArticleEntity> findLast3ByType(@Param("nameUz") String nameUz, Pageable pageable);


    @Query("SELECT a FROM ArticleEntity a " +
            "JOIN ArticleTypeEntity t " +
            "WHERE t.nameUz = :nameUz " +
            "AND a.status = 'PUBLISHED' " + // Faqat nashr qilinganlarini olish tavsiya etiladi
            "ORDER BY a.createDate DESC")
    List<ArticleEntity> findLast5ByType(@Param("nameUz") String nameUz, Pageable pageable);

    Optional<ArticleEntity> findByIdAndVisibleIsTrue(String id);

   List<ArticleEntity >findAllByVisibleIsTrue();

    @Modifying
    @Transactional
    @Query(value = "update ArticleEntity set visible = false where id = ?1")
    void updateVisible(String id);

}