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

/**
 * The type Spring security config.
 */
@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {


    /**
     * authenticationEntryPoint
     */
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    /**
     * authenticationFilter
     */
    private JwtAuthenticationFilter authenticationFilter;
    /**
     * userDetailsService
     */
    private CustomUserDetailService userDetailsService; //spring security 6 onwards no need to manually provide its reference
        // to auth manager -> when we inject in config class uses this to loadUser

    /**
     * Password encoder password encoder.
     *
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Security filter chain security filter chain.
     *
     * @param http the http
     * @return the security filter chain
     * @throws Exception the exception
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        // Set permissions on endpoints
        http.authorizeHttpRequests((authorize) ->{
            authorize.requestMatchers("/api/v1/admin/**").hasRole("ADMIN");
            authorize.requestMatchers("api/v1/public/**").hasAnyRole("ADMIN","USER");
            authorize.requestMatchers("/api/role/admin/**").hasRole("ADMIN");
            authorize.requestMatchers("/api/auth/**").permitAll();
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

    /**
     * Authentication manager authentication manager.
     *
     * @param configuration the configuration
     * @return the authentication manager
     * @throws Exception the exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * Granted authority defaults granted authority defaults.
     *
     * @return the granted authority defaults
     */
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
