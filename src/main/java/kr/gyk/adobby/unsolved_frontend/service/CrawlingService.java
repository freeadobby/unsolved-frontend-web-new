package kr.gyk.adobby.unsolved_frontend.service;

import kr.gyk.adobby.unsolved_frontend.dto.problem.ProblemContentDTO;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CrawlingService {

    public ProblemContentDTO baekjoonProblem (Integer problemId) throws Exception{
        Connection connection = getConnection("https://www.acmicpc.net/problem/" + problemId);
        return ProblemContentDTO.builder()
                .time(getElements(connection, "#problem-info > tbody > tr > td:nth-child(1)").text())
                .memory(getElements(connection, "#problem-info > tbody > tr > td:nth-child(2)").text())
                .submit(getElements(connection, "#problem-info > tbody > tr > td:nth-child(3)").text())
                .correct(getElements(connection, "#problem-info > tbody > tr > td:nth-child(4)").text())
                .accepted(getElements(connection, "#problem-info > tbody > tr > td:nth-child(5)").text())
                .correctRate(getElements(connection, "#problem-info > tbody > tr > td:nth-child(6)").text())
                .title(getElements(connection, "#problem_title").text())
                .content(getElements(connection, "#problem_description").text())
                .input(getElements(connection, "#problem_input").text())
                .output(getElements(connection, "#problem_output").text())
                .build();
    }

    public static Connection getConnection (String url) throws Exception {
        return Jsoup.connect(url);
    }

    public static Elements getElements (String url, String query) throws Exception {
        Connection connection = getConnection(url);
        return connection.get().select(query);
    }

    public static Elements getElements (Connection connection, String query) throws Exception {
        return connection.get().select(query);
    }

}
