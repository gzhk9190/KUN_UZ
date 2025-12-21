package kun.uz.repository;

import jakarta.transaction.Transactional;
import kun.uz.entities.RegionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<RegionEntity, String> {
    Optional<RegionEntity> findByNameUzAndVisibleIsTrue(String nameUz);
    List<RegionEntity> findAllByVisibleIsTrue();
    Optional<RegionEntity> findByIdAndVisibleIsTrue(String id);

    @Modifying
    @Transactional
    @Query(value = "update RegionEntity set visible = false where id = ?1")
    void updateVisible(String id);

    Optional<RegionEntity> findByNameEnAndVisibleIsTrue(String nameEn);

    Optional<RegionEntity> findByNameRuAndVisibleIsTrue(String nameRu);
    
}