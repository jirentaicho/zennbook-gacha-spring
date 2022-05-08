package com.volkruss.toaru.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;

public class JwtLoginFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("これはフィルターじゃ！てな感じでな！！！");
        // Header情報にアクセスするためにHttpServletRequestにキャストする
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        // headerからTokenを取得する
        String header = httpServletRequest.getHeader("X-AUTH-TOKEN");

        //　チェック処理
        if(header == null || !header.startsWith("Bearer ")){
            chain.doFilter(request,response);
            return;
        }
        String token = header.substring(7);
        // Tokenの検証と認証を行う
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("secret")).build().verify(token);
        // usernameの取得
        String username = decodedJWT.getClaim("username").toString();
        // ログイン状態を設定する
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username,null,new ArrayList<>()));
        chain.doFilter(request,response);
    }
}
