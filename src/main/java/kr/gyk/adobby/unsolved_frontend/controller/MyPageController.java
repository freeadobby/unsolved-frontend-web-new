package kr.gyk.adobby.unsolved_frontend.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.gyk.adobby.unsolved_frontend.dto.user.analysis.UserAnalysisDTO;
import kr.gyk.adobby.unsolved_frontend.service.CookieService;
import kr.gyk.adobby.unsolved_frontend.service.MyPageService;
import kr.gyk.adobby.unsolved_frontend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MyPageController {
    private final MyPageService myPageService;
    private final UserService userService;
    private final CookieService cookieService;

    @GetMapping()
    public String myPage (Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("userName", userService.getAuthUser(request));
        model.addAttribute("analysis", myPageService.userAnalysisGet(userService.userGet(cookieService.getAuth(request)).getEmail()));
        return "mypage/overview.html";
    }

    @GetMapping("/solved")
    public String myPageSolved (Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("userName", userService.getAuthUser(request));
        model.addAttribute("analysis", myPageService.userAnalysisGet(userService.userGet(cookieService.getAuth(request)).getEmail()));
        return "mypage/solved.html";
    }

    @GetMapping("/history")
    public String myPageHistory (Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("userName", userService.getAuthUser(request));
        model.addAttribute("analysis", myPageService.userAnalysisGet(userService.userGet(cookieService.getAuth(request)).getEmail()));
        return "mypage/history.html";
    }

    @GetMapping("/today")
    public String myPageOverview (Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("userName", userService.getAuthUser(request));
        model.addAttribute("analysis", myPageService.userAnalysisGet(userService.userGet(cookieService.getAuth(request)).getEmail()));
        return "mypage/today.html";
    }


}
