package smarttraffic.detectors_analyzer.security;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;
import smarttraffic.detectors_analyzer.util.JwtTokenUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Set;

import static org.springframework.util.StringUtils.hasText;

@Component
public class JwtTokenFilter extends GenericFilterBean {
    public static final String AUTHORIZATION = "Authorization";

    public JwtTokenFilter() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        logger.info("do filter");
        String token = getTokenFromRequest((HttpServletRequest) servletRequest);
        if (token != null && JwtTokenUtil.validateToken(token)) {
            String userLogin = JwtTokenUtil.getLoginFromToken(token);
            String requestType = JwtTokenUtil.getType(token);
            if (requestType.equals("INT") && userLogin.equals("${username}")) {
                User user = new User("trafficControlSystem");
                user.setEnabled(true);
                Role role = new Role("SMART_TRAFFIC_CONTROL");
                role.setAuthorities(Set.of(new Authority("CAN_READ"), new Authority("CAN_WRITE")));
                user.addRole(role);
                CustomUserDetails customUserDetails = new CustomUserDetails(user);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                        = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }
}