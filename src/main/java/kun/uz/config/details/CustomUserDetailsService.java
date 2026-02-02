package kun.uz.config.details;

import kun.uz.entities.ProfileEntity;
import kun.uz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

/**
 * @author 'Bilol Tuxtamurodov' on 10.12.2025
 * @project Lesson_130
 * @contact @BilolTuxtamurodov
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private ProfileRepository profileRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ProfileEntity entity = profileRepository.findByEmailAndVisibleIsTrue(username);
        if (Objects.isNull(entity)) {
            throw new UsernameNotFoundException("Bunday foydalanuvchi yo'q");
        }
        CustomUserDetails user = new CustomUserDetails();
        user.setId(entity.getId());
        user.setLogin(entity.getEmail());
        user.setRole(entity.getRole());
        return user;
    }
}
