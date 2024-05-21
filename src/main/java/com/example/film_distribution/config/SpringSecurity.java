package com.example.film_distribution.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurity {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests((authorize) ->
                        authorize.requestMatchers("/register/**").permitAll()
                                .requestMatchers("/index").permitAll()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/index/author").permitAll()
                                .requestMatchers("/admin").hasRole("ADMIN")
                                .requestMatchers("/admin/users").hasRole("ADMIN")
                                .requestMatchers("/new").hasRole("ADMIN")
                                .requestMatchers("/edit/**").hasRole("ADMIN")
                                .requestMatchers("/save").hasRole("ADMIN")
                                .requestMatchers("/delete/**").hasRole("ADMIN")
                                .requestMatchers("/delete/admin").hasRole("ADMIN")
                                .requestMatchers("/description/**").permitAll()
                                .requestMatchers("/index/cinema_list").permitAll()
                                .requestMatchers("/admin/cinema_list").hasRole("ADMIN")
                                .requestMatchers("/index/session_list").permitAll()
                                .requestMatchers("/admin/session_list").hasRole("ADMIN")
                                .requestMatchers("/new_cinema").hasRole("ADMIN")
                                .requestMatchers("/edit_cinema/**").hasRole("ADMIN")
                                .requestMatchers("/save_cinema").hasRole("ADMIN")
                                .requestMatchers("/delete_cinema/**").hasRole("ADMIN")
                                .requestMatchers("/new_session").hasRole("ADMIN")
                                .requestMatchers("/edit_session/**").hasRole("ADMIN")
                                .requestMatchers("/save_session").hasRole("ADMIN")
                                .requestMatchers("/delete_session/**").hasRole("ADMIN")


                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/admin", true)
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                );
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}