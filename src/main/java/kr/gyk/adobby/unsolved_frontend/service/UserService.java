package kr.gyk.adobby.unsolved_frontend.service;

import jakarta.servlet.http.HttpServletRequest;
import kr.gyk.adobby.unsolved_frontend.dto.cookie.AuthDTO;
import kr.gyk.adobby.unsolved_frontend.dto.user.LogoutDTO;
import kr.gyk.adobby.unsolved_frontend.dto.user.UserDTO;
import kr.gyk.adobby.unsolved_frontend.exception.Response404;
import kr.gyk.adobby.unsolved_frontend.form.LoginForm;
import kr.gyk.adobby.unsolved_frontend.form.RegisterForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserService {
    private final WebClient webClient;
    private final CookieService cookieService;

    public UserDTO userGet (AuthDTO request) throws Exception {
        Mono<UserDTO> response = null;
        response = webClient.get()
                .uri("/v1/user?email=" + request.getUserEmail())
                .header("Authorization", "Bearer " + request.getAccessToken())
                .retrieve()
                .bodyToMono(UserDTO.class);
        return response.block();
    }

    public UserDTO loginPost (LoginForm loginForm) throws Exception {
        Mono<UserDTO> response = null;
        try {
            response = webClient.post()
                    .uri("/v1/user/login")
                    .bodyValue(loginForm)
                    .retrieve()
                    .bodyToMono(UserDTO.class);
        } catch (WebClientResponseException.NotFound e) {
            throw new Response404();
        }
        return response.block();
    }

    public void logoutPost (AuthDTO authDTO) throws Exception {
        webClient.post()
                .uri("/v1/user/logout")
                .header("Authorization", "Bearer " + authDTO.getAccessToken())
                .bodyValue(LogoutDTO.builder().email(authDTO.getUserEmail()).accessToken(authDTO.getAccessToken()).build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

    public void registerPost (RegisterForm registerForm) {
        webClient.post()
                .uri("/v1/user/register")
                .bodyValue(registerForm)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }

    public String getAuthUser (HttpServletRequest request) {
        String username = null;
        try {
            username = this.userGet(cookieService.getAuth(request)).getUsername();
        } catch (Exception ignored) { }
        return username;
    }

}
