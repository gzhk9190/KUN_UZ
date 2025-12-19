package kun.uz.repository;

import kun.uz.entities.RegionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface RegionRepository extends JpaRepository<RegionEntity, String> {
    Optional<RegionEntity> findByNameUzAndVisibleIsTrue(String nameUz);
    List<RegionEntity> findAllByVisibleIsTrue();
    Optional<RegionEntity> findByIdAndVisibleIsTrue(String id);

    @Query(value = "update ProfileEntity set visible = false where id = ?1")
    void updateVisible(String id);
}