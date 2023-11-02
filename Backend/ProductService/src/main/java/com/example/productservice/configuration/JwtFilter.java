package com.example.productservice.configuration;

import com.example.productservice.exception.ErrorResponse;
import com.example.productservice.security.JwtProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
@Log4j2
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        log.info("filter invoked");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
//            if (token.startsWith("ya29")) // Google access token
//            {   // handle oauth2 access token
////                CommonOAuth2Provider
//                ClientRegistration google = clientRegistrationRepository.findByRegistrationId("google");
//                OAuth2AccessToken oAuth2AccessToken = new OAuth2AccessToken(OAuth2AccessToken.TokenType.BEARER, token, null, null);
//                OAuth2UserRequest oAuth2UserRequest = new OAuth2UserRequest(google, oAuth2AccessToken);
//                OAuth2User oAuth2User = oAuth2UserService.loadUser(oAuth2UserRequest);
//                OAuth2AuthenticationToken oauth2Token = new OAuth2AuthenticationToken(oAuth2User,oAuth2User.getAuthorities(), google.getRegistrationId());
//                SecurityContextHolder.getContext().setAuthentication(oauth2Token);
//            }
//            else {  // normal access token
                Jwt jwt  = jwtProvider.getJwtDecoder().decode(token);
                Long user_id = jwt.getClaim("user_id");
                List<String> authorities = jwt.getClaim("Authorities");
                if (user_id != null && authorities != null){
                    request.setAttribute("Shopee_User_ID", user_id);
                    request.setAttribute("Shopee_User_Authorities", authorities);
                }
//            }
        }

        filterChain.doFilter(request, response);
    }

}
