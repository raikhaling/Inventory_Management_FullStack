package com.amnil.invbackend.config;

import com.amnil.invbackend.security.CustomUserDetailService;
import com.amnil.invbackend.security.JwtAuthenticationEntryPoint;
import com.amnil.invbackend.security.JwtAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {


    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    private JwtAuthenticationFilter authenticationFilter;
    private CustomUserDetailService userDetailsService; //spring security 6 onwards no need to manually provide its reference
        // to auth manager -> when we inject in config class uses this to loadUser

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // Set permissions on endpoints
        http.authorizeHttpRequests((authorize) ->{
//            authorize.requestMatchers("/api/v1/admin/**").hasRole("ADMIN");
//            authorize.requestMatchers("api/v1/public/**").hasAnyRole("ADMIN","USER");
//            authorize.requestMatchers("/api/role/admin/**").hasRole("ADMIN");
            authorize.requestMatchers("/api/**").permitAll();
//                    authorize.requestMatchers(HttpMethod.PUT,"/api/v1/**").hasRole("ROLE_ADMIN");
//                    authorize.requestMatchers(HttpMethod.DELETE,"api/v1/**").hasRole("ROLE_ADMIN");
//                    authorize.requestMatchers(HttpMethod.GET,"api/v1/**").hasAnyRole(
//                            "ROLE_ADMIN","ROLE_USER");

                    authorize.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults());
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // Set unauthorized requests exception handler
        http.exceptionHandling(exception ->exception
                .authenticationEntryPoint(authenticationEntryPoint));

        // Add JWT token filter
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
    @Bean
    GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults(""); // Remove the ROLE_ prefix
    }



//    //in memory users
//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails nabin = User.builder()
//                .username("Nabin")
//                .password(passwordEncoder().encode("nabin"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(nabin, admin);
//    }
}
