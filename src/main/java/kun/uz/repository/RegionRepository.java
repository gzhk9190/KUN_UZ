package kun.uz.repository;

import kun.uz.entities.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface RegionRepository extends JpaRepository<RegionEntity, String> {
}