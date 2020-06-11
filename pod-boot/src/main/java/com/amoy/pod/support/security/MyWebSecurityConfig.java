package com.amoy.pod.support.security;

import com.amoy.pod.support.base.Result;
import com.amoy.pod.support.utils.SequenceUtil;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * TODO
 *
 * @author hongjiacan
 * @date 2020/6/6
 */
@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${jwt.header}")
    private String tokenHeader;
    @Value("${jwt.expiration}")
    private Long expiration;

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private MyAuthenticationFilter myAuthenticationFilter;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).
                passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        /**
         * 在 UsernamePasswordAuthenticationFilter 之前添加 myAuthenticationFilter
         */
        httpSecurity.addFilterBefore(myAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        httpSecurity
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                    .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
                    .antMatchers(
                            "/swagger-ui.html",
                            "/swagger-resources/**",
                            "/webjars/**",
                            "/v2/**"
                    ).permitAll()
                    .anyRequest().authenticated()   // 任何请求,登录后可以访问
                .and()
                    .formLogin().loginProcessingUrl("/login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .successHandler(new AuthenticationSuccessHandler() {
                        //登录成功
                        @Override
                        public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                                            HttpServletResponse httpServletResponse,
                                                            Authentication authentication) throws IOException, ServletException {
                            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                            String token = jwtTokenUtil.generateToken(userDetails);
                            String redisKey = String.format("jwt-%d-%d",
                                    SequenceUtil.nextId(), userDetails.getUsername().hashCode());
                            stringRedisTemplate.boundValueOps(redisKey).set(token, expiration, TimeUnit.SECONDS);

                            httpServletResponse.setContentType("application/json;charset=utf-8");
                            httpServletResponse.getWriter().print(new Gson().toJson(
                                    Result.ok().put(tokenHeader, redisKey)));
                        }
                    })
                    .failureHandler(new AuthenticationFailureHandler() {
                        //登录失败
                        @Override
                        public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                                            HttpServletResponse httpServletResponse,
                                                            AuthenticationException e) throws IOException, ServletException {
                            httpServletResponse.setContentType("application/json;charset=utf-8");
                            httpServletResponse.getWriter().print(new Gson().toJson(
                                    Result.error("用户名或密码错误")));
                        }
                    })
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(new AuthenticationEntryPoint() {
                        //匿名用户访问无权限资源
                        @Override
                        public void commence(HttpServletRequest httpServletRequest,
                                             HttpServletResponse httpServletResponse,
                                             AuthenticationException e) throws IOException, ServletException {
                            httpServletResponse.setContentType("application/json;charset=utf-8");
                            httpServletResponse.getWriter().print(new Gson().toJson(
                                    Result.error("登录信息已过期，请重新登录", HttpStatus.FORBIDDEN)));
                        }
                    })
                    .accessDeniedHandler(new AccessDeniedHandler() {
                        //认证过的用户访问无权限资源
                        @Override
                        public void handle(HttpServletRequest httpServletRequest,
                                           HttpServletResponse httpServletResponse,
                                           AccessDeniedException e) throws IOException, ServletException {
                            httpServletResponse.setContentType("application/json;charset=utf-8");
                            httpServletResponse.getWriter().print(new Gson().toJson(
                                    Result.error("权限不足", HttpStatus.FORBIDDEN)));
                        }
                    })
                    .and().headers().cacheControl();
    }
}
