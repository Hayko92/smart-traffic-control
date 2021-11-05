package smarttraffic.violation_service.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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

    public static HttpHeaders getHeadersWithToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(AUTHORIZATION, token);
        return headers;
    }
}