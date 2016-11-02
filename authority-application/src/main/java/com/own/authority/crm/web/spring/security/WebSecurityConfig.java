package com.own.authority.crm.web.spring.security;

import com.own.authority.crm.web.spring.security.model.LoginFilter;
import com.own.authority.crm.web.spring.security.model.LoginResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.servlet.http.HttpServletResponse;

/**
 * Created by xiemeilong on 16-1-3.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/version/**").permitAll()
                .antMatchers("/api/file").permitAll()
                .antMatchers("/api/**").authenticated()
                .and().exceptionHandling().authenticationEntryPoint((request, response, authException) -> {
                    HttpServletResponse httpResponse = (HttpServletResponse) response;
                    httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED");
                })
        ;
    }

    @Bean
    protected LoginFilter loginFilter(CustomAuthenticationSuccessHandler successHandler) throws Exception {
        LoginFilter filter = new LoginFilter("/api/auth");
        filter.setAuthenticationManager(this.authenticationManager());
        filter.setAuthenticationFailureHandler((request, response, exception) -> {
            response.setStatus(400);
            response.setCharacterEncoding("UTF-8");

            String message="";

            if(exception instanceof DisabledException) {
                message = "账号已被禁用！";
            } else {
                message = "用户名或密码错误！";
            }

            mapper.writeValue(response.getWriter(),new LoginResult(message));
        });
        filter.setAuthenticationSuccessHandler(successHandler);
        return filter;
    }


    @Bean
    public UserDetailsService userDetailsService(){
        return new StaffUserDetailsService();
    }


    @Autowired
    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService())
                .passwordEncoder(new BCryptPasswordEncoder());
    }

}