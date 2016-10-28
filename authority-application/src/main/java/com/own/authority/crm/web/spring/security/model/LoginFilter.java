package com.own.authority.crm.web.spring.security.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by xiemeilong on 14-12-23.
 */
public class LoginFilter extends AbstractAuthenticationProcessingFilter {
    public LoginFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }

        InputStreamReader reader = new InputStreamReader(request.getInputStream());
        LoginInput input = mapper.readValue(reader, LoginInput.class);
        String username = input.getUsername();
        String password = input.getPassword();

        if (username == null) {
            username = "";
        }

        if (password == null) {
            password = "";
        }

        username = username.trim();

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);

        return this.getAuthenticationManager().authenticate(authRequest);
    }
}
