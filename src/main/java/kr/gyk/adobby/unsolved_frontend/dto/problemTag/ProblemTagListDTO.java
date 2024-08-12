package kr.gyk.adobby.unsolved_frontend.dto.problemTag;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ProblemTagListDTO {
    private Integer count;
    private List<ProblemTagDTO> problemTag;
}
