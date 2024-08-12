package kr.gyk.adobby.unsolved_frontend.service;

import kr.gyk.adobby.unsolved_frontend.dto.problem.analysis.ProblemAnalysisDTO;
import kr.gyk.adobby.unsolved_frontend.dto.user.analysis.UserAnalysisDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProblemService {
    private final WebClient webClient;

    public ProblemAnalysisDTO problemAnalysisGet (int problem, String user) throws Exception {
        Mono<ProblemAnalysisDTO> response = null;
        response = webClient.get()
                .uri("/v1/problem/analysis?problem=" + problem + "&user=" + user)
                .retrieve()
                .bodyToMono(ProblemAnalysisDTO.class);
        return response.block();
    }

}
