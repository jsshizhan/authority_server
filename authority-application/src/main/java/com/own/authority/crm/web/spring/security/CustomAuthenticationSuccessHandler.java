package com.own.authority.crm.web.spring.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.own.authority.crm.web.spring.security.model.LoginResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by xiemeilong on 15-4-15.
 */
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final ObjectMapper mapper = new ObjectMapper();

    private final static String SESSION_USER_MAP_KEY = "crm-account-session-map:";


    @Autowired
    RedisOperationsSessionRepository sessionRepository;

    @Autowired
    JedisPool jedisPool;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {
        User user = (User)authentication.getPrincipal();

        try(Jedis jedis = jedisPool.getResource()){
            String sessionId = jedis.get(SESSION_USER_MAP_KEY+user.getUsername());
            if(sessionId!=null) sessionRepository.delete(sessionId);
            jedis.set(SESSION_USER_MAP_KEY+user.getUsername(), request.getSession().getId());
        }

        LoginResult result =  new LoginResult("",request.getSession().getId());
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        out.print(mapper.writeValueAsString(result));
        out.flush();
        out.close();
    }
}