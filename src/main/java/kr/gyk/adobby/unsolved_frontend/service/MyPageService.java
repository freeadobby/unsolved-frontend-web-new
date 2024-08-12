package kr.gyk.adobby.unsolved_frontend.service;

import kr.gyk.adobby.unsolved_frontend.dto.problemList.ProblemListDTO;
import kr.gyk.adobby.unsolved_frontend.dto.user.analysis.UserAnalysisDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class MyPageService {
    private final WebClient webClient;

    public UserAnalysisDTO userAnalysisGet (String user) {
        Mono<UserAnalysisDTO> response = null;
        response = webClient.get()
                .uri("/v1/user/analysis?user=" + user)
                .retrieve()
                .bodyToMono(UserAnalysisDTO.class);
        return response.block();
    }

}
