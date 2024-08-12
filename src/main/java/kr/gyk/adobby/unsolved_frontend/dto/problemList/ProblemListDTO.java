package kr.gyk.adobby.unsolved_frontend.dto.problemList;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProblemListDTO {
    private Long count;
    private List<ProblemListElementDTO> problemList;
}
