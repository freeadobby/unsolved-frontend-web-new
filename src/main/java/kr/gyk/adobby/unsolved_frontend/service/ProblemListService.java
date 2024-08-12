package kr.gyk.adobby.unsolved_frontend.service;

import kr.gyk.adobby.unsolved_frontend.dto.problemList.ProblemListDTO;
import kr.gyk.adobby.unsolved_frontend.dto.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProblemListService {
    private final WebClient webClient;

    public ProblemListDTO problemListAll () throws Exception {
        Mono<ProblemListDTO> response = null;
        response = webClient.get()
                .uri("/v1/problems/all")
                .retrieve()
                .bodyToMono(ProblemListDTO.class);
        return response.block();
    }

    public ProblemListDTO problemListTier (String stage, Integer tag) throws Exception {
        int tier = 0;
        switch (stage) {
            case "copper" -> tier += 1;
            case "bronze" -> tier += 6;
            case "silver" -> tier += 11;
            case "gold" -> tier += 16;
            case "platinum" -> tier += 21;
            case "emerald" -> tier += 26;
            case "diamond" -> tier += 31;
        }
        if (!stage.equals("unrated")) {
            if (tag == 4) tier += 1;
            else if (tag == 3) tier += 2;
            else if (tag == 2) tier += 3;
            else if (tag == 1) tier += 4;
        }

        Mono<ProblemListDTO> response = null;
        response = webClient.get()
                .uri("/v1/problems/tier?tier=" + tier)
                .retrieve()
                .bodyToMono(ProblemListDTO.class);
        return response.block();
    }

    public ProblemListDTO problemListTag (Integer tag) throws Exception {
        Mono<ProblemListDTO> response = null;
        response = webClient.get()
                .uri("/v1/problems/tag?tag=" + tag)
                .retrieve()
                .bodyToMono(ProblemListDTO.class);
        return response.block();
    }

    public ProblemListDTO problemListRecommend (String userEmail) throws Exception {
        Mono<ProblemListDTO> response = null;
        response = webClient.get()
                .uri("/v1/problems/recommend?user=" + userEmail)
                .retrieve()
                .bodyToMono(ProblemListDTO.class);
        return response.block();
    }

    public ProblemListDTO problemListReview (String userEmail) throws Exception {
        Mono<ProblemListDTO> response = null;
        response = webClient.get()
                .uri("/v1/problems/review?user=" + userEmail)
                .retrieve()
                .bodyToMono(ProblemListDTO.class);
        return response.block();
    }

}
