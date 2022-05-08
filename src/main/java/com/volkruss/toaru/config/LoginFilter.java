package com.volkruss.toaru.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class LoginFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // headerからTokenを取得する
        String header = request.getHeader("X-AUTH-TOKEN");

        //　チェック処理
        if(header == null || !header.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        String token = header.substring(7);
        // Tokenの検証と認証を行う
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("secret")).build().verify(token);
        // usernameの取得
        String username = decodedJWT.getClaim("username").asString();
        // これでダブルクォーテーションが余計に付く
        //String username = decodedJWT.getClaim("username").toString();
        // ログイン状態を設定する
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username,null,new ArrayList<>()));
        filterChain.doFilter(request,response);
    }
}
