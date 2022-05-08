package com.volkruss.toaru.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // corsの設定をする
        http.cors().configurationSource(this.corsConfigurationSource());

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/api/**").authenticated();
        http.addFilter(new JsonAuthenticationFilter(authenticationManager()));
        http.addFilterAfter(new LoginFilter(),JsonAuthenticationFilter.class);
        // 以下のようなFilterでも可能
        // http.addFilterBefore(new JwtLoginFilter(),JsonAuthenticationFilter.class);
        // CSRF用のTokenをクッキーに保存する場合
        // http.csrf().ignoringAntMatchers("/api/login").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        http.csrf().ignoringAntMatchers("/api/**");
        // これで403エラーを回避する
        // http.cors().and().csrf().disable();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        corsConfiguration.addExposedHeader("X-AUTH-TOKEN");
        corsConfiguration.addAllowedOrigin("http://localhost:8081");
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", corsConfiguration);
        return corsSource;
    }

}
