package kun.uz.config.details;

import kun.uz.enums.ProfileRole;
import lombok.Getter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author 'Bilol Tuxtamurodov' on 10.12.2025
 * @project Lesson_130
 * @contact @BilolTuxtamurodov
 */

public class CustomUserDetails implements UserDetails {
    @Getter
    String id;
    String login;
    @Getter
    ProfileRole role;

    public CustomUserDetails(String id, String login, ProfileRole role) {
        this.id = id;
        this.login = login;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public @Nullable String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return login;
    }

}
