package kr.gyk.adobby.unsolved_frontend.service;

import kr.gyk.adobby.unsolved_frontend.dto.problemList.ProblemListDTO;
import kr.gyk.adobby.unsolved_frontend.dto.problemTag.ProblemTagDTO;
import kr.gyk.adobby.unsolved_frontend.dto.problemTag.ProblemTagListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProblemTagService {
    private final WebClient webClient;

    public ProblemTagListDTO problemTagListAll () throws Exception {
        Mono<ProblemTagListDTO> response = null;
        response = webClient.get()
                .uri("/v1/tag/all")
                .retrieve()
                .bodyToMono(ProblemTagListDTO.class);
        return response.block();
    }

    public ProblemTagDTO problemTagGet (Integer id) throws Exception {
        Mono<ProblemTagDTO> response = null;
        response = webClient.get()
                .uri("/v1/tag/id?tag=" + id)
                .retrieve()
                .bodyToMono(ProblemTagDTO.class);
        return response.block();
    }

}
