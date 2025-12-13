package kun.uz.repository;

import kun.uz.entities.AttachEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttachRepository extends JpaRepository<AttachEntity, String> {
    AttachEntity getById(String id);

    void deleteCascade(String fileName);
}