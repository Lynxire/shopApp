package terabu.configs;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import terabu.filters.JwtAuthenticationFilter;


import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/user/registration")
                .requestMatchers("/user/authorization")
                .requestMatchers("/comments/allComments")
                .requestMatchers("/goods/finGoodsByName")
                .requestMatchers("/goods/findByType")
                .requestMatchers("/goods")
                .requestMatchers("/stocks/find");
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).httpBasic(withDefaults()).authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/bucket/**").hasAnyAuthority("Client", "Admin")
                        .requestMatchers("/comments/**").hasAnyAuthority("Client", "Admin")
                        .requestMatchers("/comments/delete").hasAuthority("Admin")
                        .requestMatchers("/goods/**").hasAuthority("Admin")
                        .requestMatchers("/ingredients/**").hasAuthority("Admin")
                        .requestMatchers("/orders/**").hasAnyAuthority("Client", "Admin")
                        .requestMatchers("/stocks/**").hasAuthority("Admin")
                        .requestMatchers("/data/**").hasAnyAuthority("Client", "Admin")
                        .anyRequest().authenticated())
                .exceptionHandling(ex-> ex.authenticationEntryPoint(new CustomAuthenticationEntryPoint()))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
            throws Exception {
        return config.getAuthenticationManager();
    }
}
