package terabu.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder byCryptPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers("/user/registration")
                .requestMatchers("/goods");
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).httpBasic(withDefaults()).authorizeHttpRequests(authorize->authorize
                .requestMatchers("/").permitAll()
                .requestMatchers("/bucket/**").permitAll()
                .requestMatchers("/comments/**").permitAll()
                .requestMatchers("/orders/**").permitAll()
                .requestMatchers("/data/**").permitAll()
                .requestMatchers("/swagger-ui/**").hasAuthority("Admin")
                .requestMatchers("/ingredients/**").hasAuthority("Admin")
                .requestMatchers("/stocks/**").hasAuthority("Admin")
                .requestMatchers("/stocks/**").hasAuthority("Admin")

                .anyRequest().authenticated());
        return http.build();
    }
}
