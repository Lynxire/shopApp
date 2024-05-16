package terabu.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder byCryptPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//    @Bean
//    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//        http.csrf(AbstractHttpConfigurer::disable).httpBasic(withDefaults()).authorizeHttpRequests(authorize->authorize
//                .requestMatchers(HttpMethod.DELETE, "/brands/**").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.POST, "/brands/**").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.PUT, "/brands/**").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.DELETE, "/categories/**").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.POST, "/categories/**").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.PUT, "/categories/**").hasAuthority("ADMIN")
//                //Для продолжения проекта, будет ограничивать роли здесь
//                .requestMatchers("/user/**").hasAuthority("ADMIN")
//                .requestMatchers("/").permitAll()
//                .requestMatchers("/session/login").permitAll()
//                .anyRequest().authenticated());
//        return http.build();
//    }
}
