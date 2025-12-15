package kun.uz.repository;

import kun.uz.entities.AttachEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AttachRepository extends JpaRepository<AttachEntity, String> {
    AttachEntity getById(String id);

//    @Query(value = "delete AttachEntity where originName == ?1")
//    void deleteCascade(String fileName);
}