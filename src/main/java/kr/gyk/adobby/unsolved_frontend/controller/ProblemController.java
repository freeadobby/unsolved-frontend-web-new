package kr.gyk.adobby.unsolved_frontend.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.gyk.adobby.unsolved_frontend.service.CookieService;
import kr.gyk.adobby.unsolved_frontend.service.CrawlingService;
import kr.gyk.adobby.unsolved_frontend.service.ProblemService;
import kr.gyk.adobby.unsolved_frontend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.support.ReplaceOverride;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/problem")
public class ProblemController {
    private final UserService userService;
    private final CrawlingService crawlingService;
    private final ProblemService problemService;
    private final CookieService cookieService;

    @GetMapping("/{id}")
    public String problem (@PathVariable Integer id, Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("userName", userService.getAuthUser(request));
        model.addAttribute("problemId", id);
        model.addAttribute("problemContent", crawlingService.baekjoonProblem(id));
        model.addAttribute("analysis", problemService.problemAnalysisGet(id, userService.userGet(cookieService.getAuth(request)).getEmail()));
        return "problem/detail.html";
    }

}
