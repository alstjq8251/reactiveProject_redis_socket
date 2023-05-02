//package com.socket_redis.websocket_redis.security.filter;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.socket_redis.websocket_redis.dto.LoginRequestDto;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.validation.ConstraintViolation;
//import javax.validation.ConstraintViolationException;
//import javax.validation.Validation;
//import java.io.IOException;
//import java.util.Set;
//
//
//public class UserIdPwAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
//
//    public UserIdPwAuthenticationFilter(AntPathRequestMatcher requestMatcher, AuthenticationManager authenticationManager) {
//        super(authenticationManager);
//        setRequiresAuthenticationRequestMatcher(requestMatcher);
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)throws AuthenticationException {
//
//        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
//
////        try{
//        if(request.getContentLength() == 0){
//            request.setAttribute("exception","입력해주세요");
//            throw new IllegalArgumentException("입력해주세요");
//        }
//        LoginRequestDto loginRequestDto;
//        try {
//            loginRequestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
//        } catch (IOException e) {
//            throw new RuntimeException("Unable to read request body", e);
//        }
//        validate(loginRequestDto);
////        LoginRequestDto loginRequestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
//        usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(loginRequestDto.getId(), loginRequestDto.getPassWord());
//
//        setDetails(request, usernamePasswordAuthenticationToken);
//        return this.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
//    }
//
//
//    private void validate(LoginRequestDto loginRequestDto) {
//        Set<ConstraintViolation<LoginRequestDto>> violations = Validation.buildDefaultValidatorFactory().getValidator().validate(loginRequestDto);
//        if (!violations.isEmpty()) {
//            throw new ConstraintViolationException(violations);
//        }
//    }
//}
