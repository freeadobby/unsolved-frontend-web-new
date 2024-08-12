package kr.gyk.adobby.unsolved_frontend.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.gyk.adobby.unsolved_frontend.dto.cookie.AuthDTO;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CookieService {

    @Value(".${server.domain}") private String serverDomain;

    public void setAuth (String userEmail, String accessToken, String refreshToken, HttpServletResponse response) {
        this.addCookie("authAccessToken", accessToken, 60 * 60, response);
        this.addCookie("authRefreshToken", refreshToken, 60 * 60, response);
        this.addCookie("authUserEmail", userEmail, 60 * 60, response);
    }

    public AuthDTO getAuth (HttpServletRequest request) {
        return AuthDTO.builder()
                .accessToken(this.getCookie("authAccessToken", request))
                .refreshToken(this.getCookie("authRefreshToken", request))
                .userEmail(this.getCookie("authUserEmail", request))
                .build();
    }

    public void addCookie (String key, String value, int age, HttpServletResponse response) {
        Cookie cookie = new Cookie(key, value);
        cookie.setMaxAge(age);
        cookie.setPath("/");
        //cookie.setDomain(serverDomain);
        response.addCookie(cookie);

        return;
    }

    public String getCookie (String key, HttpServletRequest request) {
        for (Cookie cookie : request.getCookies())
            if (cookie.getName().equals(key))
                return cookie.getValue();
        return null;
    }

}
