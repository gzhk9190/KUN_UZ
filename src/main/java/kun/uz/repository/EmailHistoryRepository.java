package kun.uz.repository;

import jakarta.transaction.Transactional;
import kun.uz.entities.EmailHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


import java.time.LocalDate;
import java.util.List;

public interface EmailHistoryRepository extends JpaRepository<EmailHistoryEntity, String> {

    @Transactional
    @Modifying
    @Query("select EmailHistoryEntity where EmailHistoryEntity.email = ?1")
    List<EmailHistoryEntity> findAllByEmailId(String eId);


    @Transactional
    @Modifying
    @Query("select EmailHistoryEntity where EmailHistoryEntity.createDate day = ?1")
    List<EmailHistoryEntity> findAllByGivenDate(LocalDate givenDate);

    Page<EmailHistoryEntity> findByVisible(Boolean visible, Pageable pageable);
}