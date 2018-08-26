package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final TokenUtils tokenUtils;

    @Autowired
    public WebSecurityConfig(@Qualifier("userDetailsServiceImpl")
                                     UserDetailsService userDetailsService,
                             TokenUtils tokenUtils) {
        this.userDetailsService = userDetailsService;
        this.tokenUtils = tokenUtils;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/users/**").hasAuthority("Administrator")
                .antMatchers(HttpMethod.GET, "/api/predmet/**").hasAnyAuthority("Administrator", "Nastavnik", "Ucenik")
                .antMatchers(HttpMethod.POST, "/api/predmet/*/ucenik/**").hasAnyAuthority("Administrator", "Nastavnik")
                .antMatchers(HttpMethod.DELETE, "/api/predmet/*/ucenik/**").hasAnyAuthority("Administrator", "Nastavnik")
                .antMatchers("/api/predmet/**").hasAuthority("Administrator")
                .antMatchers(HttpMethod.GET, "/api/nastavna_aktivnost_tip/**").hasAnyAuthority("Administrator", "Nastavnik")
                .antMatchers("/api/nastavna_aktivnost_tip/**").hasAuthority("Administrator")
                .antMatchers("/api/nastavna_aktivnost/**").hasAnyAuthority("Administrator", "Nastavnik")
                .antMatchers(HttpMethod.GET, "/api/uplata/**").hasAnyAuthority("Administrator", "Ucenik")
                .antMatchers("/api/uplata/**").hasAuthority("Administrator")
                .antMatchers(HttpMethod.DELETE, "/api/dokument/**").hasAuthority("Administrator")
                .antMatchers("/api/dokument/**").hasAnyAuthority("Administrator", "Ucenik")
                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(authenticationTokenFilterBean(),
                UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
        AuthenticationTokenFilter authenticationTokenFilter =
                new AuthenticationTokenFilter(tokenUtils, userDetailsService);
        authenticationTokenFilter.setAuthenticationManager(authenticationManagerBean());
        return authenticationTokenFilter;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
