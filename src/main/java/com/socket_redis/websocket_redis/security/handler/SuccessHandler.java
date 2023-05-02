//package com.socket_redis.websocket_redis.security.handler;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.nio.charset.StandardCharsets;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//@RequiredArgsConstructor
//public class SuccessHandler implements AuthenticationSuccessHandler {
////
////    private final TokenProvider tokenProvider;
////    private final PeriodTblRepository periodTblRepository;
//
//    @Override
//    @Transactional
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
//
//        response.setCharacterEncoding(StandardCharsets.UTF_8.toString());
//
////        Object object = authentication.getPrincipal();
////        OAuth2UserInfo oAuth2UserInfo;
////
////        boolean ci = false;
////        boolean social = false;
////        String uri;
////        boolean persist = false;
////        String pk = "";
////        String email = "";
////        String social_type = "none";
////        String name = "";
////        String pw = "";
////        if(authentication instanceof UserToken){
////            log.info("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
////            log.info(String.valueOf(((UserToken) authentication).getUser().isSchoolMaster()));
////            log.info("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
////            log.info(((UserToken) authentication).getUser().getUsername());
////            ci = ((UserToken) authentication).getUser().isSchoolMaster() || ((UserToken) authentication).getUser().getMember().getMemberMaster() != null;
////            pk = ((UserToken) authentication).getUser().getMember().getId();
////            persist = true;
////            email = ((UserToken) authentication).getUser().getMember().getEmail();
////            name = ((UserToken) authentication).getUser().getMember().getName();
////            pw = ((UserToken) authentication).getUser().getMember().getPasswd();
////            ResponseEntity<FormLoginResponseDto> responseEntity = ResponseEntity.ok().body(
////                    FormLoginResponseDto.builder()
////                            .status(true)
////                            .message("로그인이 성공하셨습니다.")
////                            .ci(ci)
////                            .pk_token(tokenProvider.generatePkToken(pk,social_type,email,persist,name,pw))
////                            .persist(true)
////                            .build());
////            ObjectMapper objectMapper = new ObjectMapper();
////            response.setStatus(HttpStatus.OK.value());
////            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
////            objectMapper.writeValue(response.getOutputStream(),responseEntity);
////            return;
////        }else if(object instanceof OAuth2UserInfo){
////            log.info("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
////            log.info(String.valueOf(((OAuth2UserInfo) object).getSchoolMaster()));
////            log.info("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ");
////            oAuth2UserInfo = (OAuth2UserInfo) object;
////            if (((OAuth2UserInfo) object).getSchoolMaster()) {
////                ci = true;
////            } else {
////                ci = oAuth2UserInfo.isIntegrate();
////            }
////            persist = !oAuth2UserInfo.getLogin();
////            Oauth2UserInfoStrategy oauth2UserInfoStrategy = findOauth2Strategy(oAuth2UserInfo);
////            email = oauth2UserInfoStrategy.process(oAuth2UserInfo);
////            if(persist){
////                email = oAuth2UserInfo.getUser().getEmail();
////                pk = oAuth2UserInfo.getUser().getId();
////                pw = oAuth2UserInfo.getUser().getPasswd();
////            }
////            social_type = oAuth2UserInfo.getRegistrationId();
////
////            social = true;
////            name = oAuth2UserInfo.getName();
////        }
//    }
//}