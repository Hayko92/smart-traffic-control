package smarttraffic.cameraimitation.security;


import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import smarttraffic.cameraimitation.exception.SmartTrafficControlException;
import smarttraffic.cameraimitation.util.JwtTokenUtil;
import smarttraffic.cameraimitation.util.RoleValues;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.util.StringUtils.hasText;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {
    public static final String AUTHORIZATION = "Authorization";

    public JwtTokenFilter() {
    }


    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest,
                                    HttpServletResponse servletResponse, FilterChain filterChain) {
        logger.info("do filter");
        String token = getTokenFromRequest(servletRequest);
        if (token != null && JwtTokenUtil.validateToken(token)) {
            String userLogin = JwtTokenUtil.getLoginFromToken(token);
            String requestType = JwtTokenUtil.getType(token);
            if (requestType.equals("INT") && userLogin.equals("${username}")) {
                User user = new User("trafficControlSystem");
                user.setEnabled(true);
                Role role = new Role(RoleValues.SYSTEM.name());
                user.addRole(role);
                CustomUserDetails customUserDetails = new CustomUserDetails(user);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (IOException | ServletException e) {
            throw new SmartTrafficControlException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        } else return bearer;
    }
}