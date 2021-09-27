package com.udemy.socialnetwork.configuration;

import com.udemy.socialnetwork.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    AppUserService appUserService;

    protected void configure(HttpSecurity http) throws Exception {
        http
                //.csrf().disable()
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/about",
                        "/register",
                        "/search",
                        "/search/*",
                        "/registrationconfirmed",
                        "/expiredtoken",
                        "/invaliduser",
                        "/verifyemail",
                        "/profilephoto",
                        "/profilephoto/*",
                        "/confirmregister",
                        "/message/*",
                        "/app/message/*")
                .permitAll()
                .antMatchers(
                        "/js/*",
                        "/css/*",
                        "/img/*")
                .permitAll()
                .antMatchers("/addStatus",
                                        "/viewStatus",
                                        "/editStatus",
                                        "/deleteStatus")
                .hasRole("ADMIN")
                .antMatchers(
                        "/profile",
                                    "/profile/*",
                                    "/edit-profile-about",
                                    "/upload-profile-photo",

                                    "/save-interest",
                                    "/delete-interest",
                                    "/webjars/**",
                                    "/chat/**",
                                    "/app/*",
                                    "/chatview/*",
                                    "/message/send/*"
                )
                .authenticated()
                .anyRequest()
                .denyAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .permitAll()
                .and()
                .logout();
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("nick")
//                .password("{noop}hello")
//                .roles("USER");
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserService).passwordEncoder(passwordEncoder);
    }
}
