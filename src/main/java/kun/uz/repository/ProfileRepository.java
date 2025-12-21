package kun.uz.repository;

import jakarta.transaction.Transactional;
import kun.uz.entities.ProfileEntity;
import kun.uz.enums.ProfileStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, String> {
    Optional<ProfileEntity> findByEmailAndVisibleIsTrue(String email);

    Optional<ProfileEntity> findByEmailAndVisibleIsTrueAndStatus(String email, ProfileStatus status);


    Optional<ProfileEntity> findByPhoneAndVisibleIsTrueAndStatus(String phone, ProfileStatus profileStatus);

    Optional<ProfileEntity> findByIdAndVisibleIsTrue(String id);

    Optional<ProfileEntity> findAllByVisibleIsTrue();
    @Modifying
    @Transactional
    @Query(value = "update ProfileEntity set visible = false where id = ?1")
    void updateVisible(String id);
}