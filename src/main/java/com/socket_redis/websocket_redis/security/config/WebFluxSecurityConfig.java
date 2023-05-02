package com.socket_redis.websocket_redis.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@RequiredArgsConstructor
@EnableWebFluxSecurity
public class WebFluxSecurityConfig  {

//    private final ReactiveUserDetailsService reactiveUserDetailsService;
//
//    private final PasswordEncoder passwordEncoder;
//
//    private final AntPathRequestMatcher LOGIN_REQUEST_MATCHER = new AntPathRequestMatcher("/auth/login", "POST");


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
//        UserIdPwAuthenticationFilter userInfoAuthenticationFilter = new UserIdPwAuthenticationFilter(LOGIN_REQUEST_MATCHER, reactiveAuthenticationManager());
        // userInfoAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());

        http.csrf().disable();
        http
                .headers().frameOptions().disable()
                .and()
                .cors().and()
                .exceptionHandling()
                .and()
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS, "/**").permitAll() // CORS configuration
                // .antMatchers(PERMIT_URL_ARRAY).permitAll() // For Swagger API
                .pathMatchers("/auth/**").permitAll()
                .pathMatchers("/h2-console/**").permitAll()
                .pathMatchers("/test/**").permitAll()
                .anyExchange().authenticated() // All other APIs require authentication
                .and();
//                .addFilterAt(userInfoAuthenticationFilter, SecurityWebFiltersOrder.AUTHENTICATION);

        return http.build();
    }

//    @Bean
//    public WebFilter ignorePathsWebFilter() {
//        return (exchange, chain) -> {
//            if (exchange.getRequest().getURI().getPath().startsWith("/h2-console") ||
//                    exchange.getRequest().getURI().getPath().startsWith("/favicon.ico")) {
//                return chain.filter(exchange);
//            }
//            return chain.filter(exchange);
//        };
//    }
//    @Bean
//    public ReactiveAuthenticationManager reactiveAuthenticationManager() {
//        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager =
//                new UserDetailsRepositoryReactiveAuthenticationManager(reactiveUserDetailsService);
//        authenticationManager.setPasswordEncoder(passwordEncoder);
//        return authenticationManager;
//    }
}
//
//
//@RequiredArgsConstructor
//@EnableWebSecurity(debug = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final AntPathRequestMatcher LOGIN_REQUEST_MATCHER = new AntPathRequestMatcher("/auth/login","POST");
//
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring()
//                .antMatchers("/h2-console/**", "/favicon.ico");
//        web.debug(true);
//    }
//
//
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        UserIdPwAuthenticationFilter userInfoAuthenticationFilter = new UserIdPwAuthenticationFilter(LOGIN_REQUEST_MATCHER, authenticationManagerBean());
////        userInfoAuthenticationFilter.setAuthenticationSuccessHandler(customLoginSuccessHandler());
//
//        http.csrf().disable();
//        http
//                .headers()
//                .frameOptions()
//                .sameOrigin()
//
////                .and()
////                .sessionManagement()
////
//                .and()
//                .cors().and()
//                .exceptionHandling()
//                .and()
//                .authorizeRequests()
//
//                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll() // CORS설정 관련 security 열어주기
////                .antMatchers(PERMIT_URL_ARRAY).permitAll() // swagger사용을 위해 api 열기
//                .antMatchers("/auth/**").permitAll()
//                .antMatchers("/test/**").permitAll()
//                .anyRequest().authenticated()   // 나머지 API 는 전부 인증 필요;
//                .and()
//                .addFilterAt(userInfoAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//
//    }
//
////    @Bean
////    public CorsConfigurationSource corsConfigurationSource() {
////        CorsConfiguration configuration = new CorsConfiguration();
////        configuration.setAllowedOrigins(Arrays.asList("https://dev-manage.sfstory.co.kr"));
////        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE", "OPTIONS"));
////        configuration.setAllowedHeaders(Arrays.asList("*"));
////        configuration.setAllowCredentials(true);
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
////        source.registerCorsConfiguration("/apis/**", configuration);
////        return source;
////    }
//
//}