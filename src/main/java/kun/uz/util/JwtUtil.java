package kun.uz.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import kun.uz.dto.JwtDTO;
import kun.uz.enums.ProfileRole;
import kun.uz.exp.AppForbiddenException;
import kun.uz.exp.TokenNotValidException;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 'Bilol Tuxtamurodov' on 03.12.2025
 * @project Lesson_126
 * @contact @BilolTuxtamurodov
 */

public class JwtUtil {
    private static final int tokenLiveTime = 1000 * 3600 * 24 * 2;
    private static final String secretKey = "SalomMenDasturchimanMengaQandaySavollaringizBor";
    public static String getIdFromHeader(HttpServletRequest request, ProfileRole... requiredRoles) {
        try {
            JwtDTO dto = (JwtDTO) request.getAttribute("jwtDto");

            if (requiredRoles == null || requiredRoles.length == 0) {
                return dto.getId();
            }
            for (ProfileRole role : requiredRoles) {
                if (role.equals(dto.getRole())) {
                    return dto.getId();
                }
            }
        } catch (RuntimeException e) {

            throw new TokenNotValidException("Not Authorized");
        }
        throw new AppForbiddenException("Not Access");
    }
    public static String encode(String id, String phone, String role) {
        Map<String, Object> claimsMap = new HashMap<>();
        claimsMap.put("phone", phone);
        claimsMap.put("role", role);

        return Jwts.
                builder()
                .claims(claimsMap)
                .issuedAt(new Date(System.currentTimeMillis()))
                .subject(id)
                .issuer("Force turtles")
                .expiration(new Date(System.currentTimeMillis() + tokenLiveTime))
                .signWith(secretKey())
                .compact();
    }

    public static JwtDTO decode(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

        String phone = (String) claims.get("phone");
        String role = (String) claims.get("role");
        String id = claims.getSubject();
        return new JwtDTO(id, phone, role);
    }

    private static SecretKey secretKey() {
        byte [] bytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(bytes);
    }
}
