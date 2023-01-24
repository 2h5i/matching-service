package com.sparta.matchingservice.security.config;


import com.sparta.matchingservice.security.exception.CustomAccessDeniedHandler;
import com.sparta.matchingservice.security.util.JwtAuthFilter;
import com.sparta.matchingservice.security.util.JwtUtil;
import com.sparta.matchingservice.security.exception.CustomAuthenticationFailureHandler;
import com.sparta.matchingservice.security.exception.CustomAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // preAuthorize 허용부분
public class WebSecurityConfig {
    private final JwtUtil jwtUtil;

    private final RedisTemplate redisTemplate;
    private final CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    private final CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web -> web.ignoring().requestMatchers(PathRequest.toH2Console()));
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity.csrf().disable();
        // csrf/xss < 키워드로 검색해보기.

        httpSecurity.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        //세션 안쓰니까 STESELESS 쓴거임.

        httpSecurity.authorizeRequests().requestMatchers("/api/auth/**").permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(new JwtAuthFilter(jwtUtil, redisTemplate), UsernamePasswordAuthenticationFilter.class);
        // permitAll로 유저 회원가입/로그인 api는 열고, 나머지는 authenticated 되어야함.
        // 그리고 이 모든 작업 전에 JwtAuthFilter , UsernamePasswordAuth~ 필터 거쳐야함.

        httpSecurity.formLogin().disable();
        // formlogin 사용하지 않으므로 디스에이블

        /*
        httpSecurity
                .formLogin()
                .loginProcessingUrl("/api/auth/login")
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
        ;
        */

        httpSecurity.exceptionHandling().accessDeniedHandler(new CustomAccessDeniedHandler() {});
        // 인가실패 핸들링
        return httpSecurity.build();
    }
}
