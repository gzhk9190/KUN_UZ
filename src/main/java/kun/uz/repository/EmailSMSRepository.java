package kun.uz.repository;

import kun.uz.entities.EmailSMSEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailSMSRepository extends JpaRepository<EmailSMSEntity, String> {
    Optional<EmailSMSEntity> findTop1ByUsedIsFalseAndEmailAndVisibleIsTrueOrderByCreateDateDesc(String email);

    EmailSMSEntity getByEmailAndVisibleIsTrue(String email, Boolean visible);
}