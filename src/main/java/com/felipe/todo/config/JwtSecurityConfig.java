package com.felipe.todo.config;

import com.felipe.todo.security.JwtAuthenticationEntryPoint;
import com.felipe.todo.security.JwtAuthenticationProvider;
import com.felipe.todo.security.JwtAuthenticatorTokenFilter;
import com.felipe.todo.security.JwtSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtAuthenticationProvider authenticationProvider;
    private JwtAuthenticationEntryPoint entryPoint;

    public JwtSecurityConfig(JwtAuthenticationProvider authenticationProvider, JwtAuthenticationEntryPoint entryPoint) {
        this.authenticationProvider = authenticationProvider;
        this.entryPoint = entryPoint;
    }

    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }

    @Bean
    public JwtAuthenticatorTokenFilter authenticatorTokenFilter() {
        JwtAuthenticatorTokenFilter filter = new JwtAuthenticatorTokenFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setAuthenticationSuccessHandler(new JwtSuccessHandler());

        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("**/api/**").authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(entryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(authenticatorTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();
    }
}
