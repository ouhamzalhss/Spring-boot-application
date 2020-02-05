package com.ouhamza.devoxx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Random;

@SpringBootApplication
@EnableGlobalMethodSecurity(securedEnabled = true)
public class DevoxxApplication {

    public static void main(String[] args) {

        SpringApplication.run(DevoxxApplication.class, args);
    }

    @Configuration
    static class AppSecurityConfig extends GlobalAuthenticationConfigurerAdapter {

        @Bean
        public UserDetailsService userDetailsService() {

            User.UserBuilder users = User.withDefaultPasswordEncoder();
            InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
            manager.createUser(users.username("user").password("user").roles("USER").build());
            manager.createUser(users.username("hero").password("hero").roles("USER", "HERO").build());
            return manager;

        }
    }

    //add custom health
    @Bean
    public HealthIndicator devoxxHealthIndicator(){
        return () -> {
            if(new Random().nextBoolean()){
                return Health.up().build();
            }else{
                return Health.down().withDetail("booooo",42).build();
            }
        };
    }

}
