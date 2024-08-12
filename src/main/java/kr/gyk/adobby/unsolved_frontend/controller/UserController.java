package kr.gyk.adobby.unsolved_frontend.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import kr.gyk.adobby.unsolved_frontend.dto.cookie.AuthDTO;
import kr.gyk.adobby.unsolved_frontend.dto.user.UserDTO;
import kr.gyk.adobby.unsolved_frontend.form.LoginForm;
import kr.gyk.adobby.unsolved_frontend.form.RegisterForm;
import kr.gyk.adobby.unsolved_frontend.service.CookieService;
import kr.gyk.adobby.unsolved_frontend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import reactor.netty.http.server.HttpServerResponse;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final CookieService cookieService;

    @GetMapping("/login")
    public String login (LoginForm loginForm) {
        return "user/login.html";
    }

    @PostMapping("/user/login")
    public String loginPost (@Valid LoginForm loginForm, HttpServletResponse response) throws Exception {
        UserDTO loginResponseDTO = null;

        try {
            loginResponseDTO = userService.loginPost(loginForm);
        } catch (Exception e) {
            return "redirect:/login";
        }
        cookieService.setAuth(loginResponseDTO.getEmail(), loginResponseDTO.getToken().getAccessToken(), loginResponseDTO.getToken().getRefreshToken(), response);

        return "redirect:/";
    }

    @GetMapping("/register")
    public String register (RegisterForm registerForm) {
        return "user/register.html";
    }

    @PostMapping("/user/register")
    public String registerPost (@Valid RegisterForm registerForm) {
        userService.registerPost(registerForm);
        return "redirect:/";
    }

    @GetMapping("/user/logout")
    public String logout (HttpServletRequest request, HttpServletResponse response) throws Exception {
        AuthDTO authDTO = null;
        try {
            authDTO = cookieService.getAuth(request);
        } catch (Exception e) {
            cookieService.setAuth(null, null, null, response);
            return "redirect:/";
        }
        userService.logoutPost(authDTO);
        cookieService.setAuth(null, null, null, response);
        return "redirect:/";
    }

}
