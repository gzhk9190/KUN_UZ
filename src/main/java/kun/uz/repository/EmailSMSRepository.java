package kun.uz.repository;

import jakarta.transaction.Transactional;
import kun.uz.entities.EmailSMSEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmailSMSRepository extends JpaRepository<EmailSMSEntity, String> {
    Optional<EmailSMSEntity> findTop1ByUsedIsFalseAndEmailAndVisibleIsTrueOrderByCreateDateDesc(String email);


    EmailSMSEntity getByEmailAndVisibleIsTrue(String email, Boolean visible);

    Optional<EmailSMSEntity> findByIdAndVisibleIsTrue(String id);

    List<EmailSMSEntity> findAllByVisibleIsTrue();
    @Modifying
    @Transactional
    @Query(value = "update EmailSMSEntity set visible = false where id = ?1")
    void updateVisible(String id);

}