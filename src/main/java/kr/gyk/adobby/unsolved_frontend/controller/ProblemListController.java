package kr.gyk.adobby.unsolved_frontend.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.gyk.adobby.unsolved_frontend.service.CookieService;
import kr.gyk.adobby.unsolved_frontend.service.ProblemListService;
import kr.gyk.adobby.unsolved_frontend.service.ProblemTagService;
import kr.gyk.adobby.unsolved_frontend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/problems")
public class ProblemListController {
    private final UserService userService;
    private final ProblemListService problemListService;
    private final ProblemTagService problemTagService;
    private final CookieService cookieService;

    @GetMapping()
    public String problemList (Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("userName", userService.getAuthUser(request));
        model.addAttribute("problemTagList", problemTagService.problemTagListAll());
        return "problem/overview.html";
    }

    @GetMapping("/all")
    public String problemListAll (Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("userName", userService.getAuthUser(request));
        model.addAttribute("menu", "모든 문제");
        model.addAttribute("problemList", problemListService.problemListAll());
        return "problem/list.html";
    }

    @GetMapping("/tier/{stage}/{tag}")
    public String problemListTier (@PathVariable("stage") String stage, @PathVariable("tag") Integer tag, Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("userName", userService.getAuthUser(request));
        model.addAttribute("menu", "난이도별 문제 / " + stage + tag);
        model.addAttribute("stage", stage);
        model.addAttribute("tag", tag);
        model.addAttribute("problemList", problemListService.problemListTier(stage, tag));
        return "problem/list.html";
    }

    @GetMapping("/tag/{tag}")
    public String problemListTag (@PathVariable("tag") Integer tag, Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("userName", userService.getAuthUser(request));
        model.addAttribute("menu", "태그별 문제 / " + problemTagService.problemTagGet(tag).getName());
        model.addAttribute("tag", tag);
        model.addAttribute("problemList", problemListService.problemListTag(tag));
        return "problem/list.html";
    }

    @GetMapping("/recommend")
    public String problemListRecommend (Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("userName", userService.getAuthUser(request));
        model.addAttribute("menu", "추천 문제");
        model.addAttribute("problemList", problemListService.problemListRecommend(userService.userGet(cookieService.getAuth(request)).getEmail()));
        return "problem/list.html";
    }

    @GetMapping("/review")
    public String problemListReview (Model model, HttpServletRequest request) throws Exception {
        model.addAttribute("userName", userService.getAuthUser(request));
        model.addAttribute("menu", "복습 문제");
        model.addAttribute("problemList", problemListService.problemListReview(userService.userGet(cookieService.getAuth(request)).getEmail()));
        return "problem/list.html";
    }

}
