package kr.gyk.adobby.unsolved_frontend.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.gyk.adobby.unsolved_frontend.dto.problemList.ProblemListDTO;
import kr.gyk.adobby.unsolved_frontend.dto.problemList.ProblemListElementDTO;
import kr.gyk.adobby.unsolved_frontend.service.CookieService;
import kr.gyk.adobby.unsolved_frontend.service.ProblemListService;
import kr.gyk.adobby.unsolved_frontend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class IndexController {
    private final CookieService cookieService;
    private final ProblemListService problemListService;
    private final UserService userService;

    @GetMapping()
    public String index (Model model, HttpServletRequest request) throws Exception {
        String userName = userService.getAuthUser(request);
        model.addAttribute("userName", userName);
        if (userName != null) {
            ProblemListDTO problemListRecDTO = problemListService.problemListRecommend(userService.userGet(cookieService.getAuth(request)).getEmail());
            ProblemListElementDTO [] problemListRecommend1 = {problemListRecDTO.getProblemList().get(0), problemListRecDTO.getProblemList().get(1), problemListRecDTO.getProblemList().get(2)};
            ProblemListElementDTO [][] problemListRecommend23 = {{problemListRecDTO.getProblemList().get(3), problemListRecDTO.getProblemList().get(4), problemListRecDTO.getProblemList().get(5)},
                                                                {problemListRecDTO.getProblemList().get(6), problemListRecDTO.getProblemList().get(7), problemListRecDTO.getProblemList().get(8)}};
            model.addAttribute("problemListRecommend1", problemListRecommend1);
            model.addAttribute("problemListRecommend23", problemListRecommend23);

            ProblemListDTO problemListRevDTO = problemListService.problemListReview(userService.userGet(cookieService.getAuth(request)).getEmail());
            ProblemListElementDTO [] problemListReview1 = {problemListRevDTO.getProblemList().get(0), problemListRevDTO.getProblemList().get(1), problemListRevDTO.getProblemList().get(2)};
            ProblemListElementDTO [][] problemListReview23 = {{problemListRevDTO.getProblemList().get(3), problemListRevDTO.getProblemList().get(4), problemListRevDTO.getProblemList().get(5)},
                                                                {problemListRevDTO.getProblemList().get(6), problemListRevDTO.getProblemList().get(7), problemListRevDTO.getProblemList().get(8)}};
            model.addAttribute("problemListReview1", problemListReview1);
            model.addAttribute("problemListReview23", problemListReview23);
        } else {
            model.addAttribute("problemListRecommend", null);
            model.addAttribute("problemListReview", null);
        }
        return "index.html";
    }

}