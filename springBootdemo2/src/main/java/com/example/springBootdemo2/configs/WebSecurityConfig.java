//package com.example.springBootdemo2.configs;
//
//import com.example.springBootdemo2.service.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//
//@Configuration
//@EnableWebSecurity
//@ComponentScan(value = "com.example.springBootdemo2")
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//
//    @Qualifier("")
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//
////    @Autowired
////    public WebSecurityConfig(@Qualifier("userServiceImpl") UserDetailsService userDetailsService) {
////        this.userDetailsService = userDetailsService;
////    }
//
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()
//                .successHandler(new SuccessUserHandler())
//                .loginProcessingUrl("/login")
//
//                .usernameParameter("j_username")
//                .passwordParameter("j_password")
//
//                .permitAll();
//
//        http.logout()
//                .permitAll()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login")
//                .and().csrf().disable();
//
//        http
//                .authorizeRequests()
//                .antMatchers("/login").anonymous()
//                // .antMatchers("/").permitAll()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .and()
//                .logout().logoutSuccessUrl("/");
//
//    }
//
//    //?????????????????? ????????????
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Autowired
//    public BCryptPasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        authenticationProvider.setUserDetailsService(userDetailsService);
//        return authenticationProvider;
//    }
//
//}

package com.example.springBootdemo2.configs;

import com.example.springBootdemo2.configs.SuccessUserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@ComponentScan(value = "com.example.springBootdemo2")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {



    @Autowired
    private UserDetailsService userDetailsService;




    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .successHandler(new SuccessUserHandler())

                // ?????????????????? action ?? ?????????? ????????????
                .loginProcessingUrl("/login")
                // ?????????????????? ?????????????????? ???????????? ?? ???????????? ?? ?????????? ????????????
                .usernameParameter("j_username")
                .passwordParameter("j_password")
                // ???????? ???????????? ?? ?????????? ???????????? ????????
                .permitAll();

        http.logout()
                // ?????????????????? ???????????? ???????????? ????????
                .permitAll()
                // ?????????????????? URL ??????????????
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                // ?????????????????? URL ?????? ?????????????? ??????????????
                .logoutSuccessUrl("/")
                //???????????????? ?????????????????????????? ?????????????????????? (???? ?????????? ???????????????? ??????????????)
                .and().csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/user").hasAnyAuthority("ROLE_USER", "ROLE_ADMIN")
                .antMatchers("/adduser").hasAnyAuthority("ROLE_ADMIN")

                .antMatchers("/edituser/*").hasAnyAuthority("ROLE_ADMIN")
                .antMatchers("/admin").hasAnyAuthority("ROLE_ADMIN");
    }

       @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


}