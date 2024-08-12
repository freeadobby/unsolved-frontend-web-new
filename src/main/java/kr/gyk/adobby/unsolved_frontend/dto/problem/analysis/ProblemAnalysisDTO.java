package kr.gyk.adobby.unsolved_frontend.dto.problem.analysis;

import kr.gyk.adobby.unsolved_frontend.dto.user.analysis.UserSignDTO;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProblemAnalysisDTO {
    private ProblemDetailDTO problem;
    private UserSignDTO user;

    private Integer scoreAnalysis;
    private Integer scoreProblem;
    private Integer scoreUser;

    private String opinion;
    private Integer totalScore;
}
