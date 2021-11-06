package smarttraffic.vehicle_service.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import smarttraffic.vehicle_service.security.Role;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;


@PropertySource("classpath:application.properties")
public final class JwtTokenUtil {


    static final String secretKey = "smart_traffic_control";

    private JwtTokenUtil() {
    }

    public static String generateToken(String login) {

        Date date = Date.from(LocalDate.now().plusYears(10).atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .claim("type", "INT")
                .compact();
    }

    public static boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            return true;
        } catch (Exception expEx) {
            return false;
        }
    }

    public static String getLoginFromToken(String token) {
        if (token.startsWith("Bearer ")) token = token.substring(7);
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public static boolean checkTokenValidation(String token) {
        return validateToken(token) && getLoginFromToken(token).equals("${username}") && getType(token).equals("INT");
    }

    public static String getType(String token) {
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
        return (String) claims.get("type");
    }


    public static Collection<GrantedAuthority> getGrantedAuthorities(String token) {
        String x = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().get("roles").toString();
        // String rolesString = claims.get("roles").toString();
        Set<Role> roles = Arrays.stream(x.split(","))
                .map(Role::new)
                .collect(Collectors.toSet());
        Collection<GrantedAuthority> authorities = roles.stream()
                .map(e -> new SimpleGrantedAuthority(e.getAuthority())).collect(Collectors.toSet());
        return authorities;
    }

    public static HttpHeaders getHeadersWithToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        if (token.startsWith("Bearer ")) token = token.substring(7);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(AUTHORIZATION, token);
        return headers;
    }
}