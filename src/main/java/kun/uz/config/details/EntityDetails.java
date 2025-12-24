package kun.uz.config.details;

import kun.uz.entities.ProfileEntity;
import kun.uz.enums.ProfileRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author 'Bilol Tuxtamurodov' on 24.12.2025
 * @project kun_uz_azam
 * @contact @BilolTuxtamurodov
 */
public class EntityDetails {
    private static CustomUserDetails getEntity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (CustomUserDetails) authentication.getPrincipal();
    }

    public static String getId() {
        return getEntity().getId();
    }

    public static ProfileRole getRole() {
        return getEntity().getRole();
    }
}
