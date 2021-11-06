package smarttraffic.vehicle_service.security;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import smarttraffic.vehicle_service.util.JwtTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

import static org.springframework.util.StringUtils.hasText;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION = "Authorization";

    public JwtTokenFilter() {
    }

    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest,
                                    HttpServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        String token = getTokenFromRequest(servletRequest);
        if (token != null && JwtTokenUtil.validateToken(token)) {
            String requestType = JwtTokenUtil.getType(token);
            if (requestType.equals("EXT")) {
                Collection<GrantedAuthority> authorities = JwtTokenUtil.getGrantedAuthorities(token);
                String userLogin = JwtTokenUtil.getLoginFromToken(token);
                CustomUserDetails customUserDetails = new CustomUserDetails(userLogin, authorities);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        customUserDetails, null, customUserDetails.getGrantedAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else if (requestType.equals("INT")) {
                String userLogin = JwtTokenUtil.getLoginFromToken(token);
                if (userLogin.equals("${username}")) {
                    User user = new User("trafficControlSystem");
                    user.setEnabled(true);
                    Role role = new Role("SYSTEM");
                    user.addRole(role);
                    CustomUserDetails customUserDetails = new CustomUserDetails(user);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                            = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        }
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        logger.info(request.getRequestURL().toString());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        } else return bearer;
    }
}