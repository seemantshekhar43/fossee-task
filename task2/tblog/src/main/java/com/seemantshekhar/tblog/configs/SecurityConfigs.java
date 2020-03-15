package com.seemantshekhar.tblog.configs;

import com.seemantshekhar.tblog.services.BlogUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Spring Security Configurations
 */
@Configuration
@EnableWebSecurity
public class SecurityConfigs {

    @Autowired
    BlogUserDetailsService service;

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Configuration
    public static class WebSecurity extends WebSecurityConfigurerAdapter {

        /**
         * Authorization access for users
         * "/", "/posts", "/edit/**", "/posts/**" can be accessed by anyone
         * "/login", "/register" can only be accessed by anonymous users
         * "/new", "/logout" can be accessed by only registered and logged in users
         *
         * default success url on log in is root "/"
         * default success url on log out is root "/"
         */
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/new").hasRole("USER")
                    .antMatchers("/posts").permitAll()
                    .antMatchers("/edit/**").permitAll()
                    .antMatchers("/posts/**").permitAll()
                    .antMatchers("/login").anonymous()
                    .antMatchers("/register").anonymous()
                    .and().formLogin()
                    .loginProcessingUrl("/perform_login")
                    .failureUrl("/login.html?error=true")
                    .defaultSuccessUrl("/")
                    .and().logout()
                    .logoutSuccessUrl("/")
            ;


        }
    }
}
