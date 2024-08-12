package kr.gyk.adobby.unsolved_frontend.dto.problemTag;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProblemTagDTO {
    private Integer id;
    private Integer idBOJ;
    private String idSolvedAC;
    private String name;
}
