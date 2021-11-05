package smarttraffic.violations_analyzer_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    private JwtTokenFilter jwtFilter;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.httpBasic().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().authorizeRequests()
//                .antMatchers("/admin/*").hasRole("ADMIN")
//                .antMatchers("/*").hasAnyRole("SMART_TRAFFIC_CONTROL", "ADMIN")
//                .and().
//                addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//        super.configure(http);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}