package kr.gyk.adobby.unsolved_frontend.dto.problemList;

import kr.gyk.adobby.unsolved_frontend.dto.problemTag.ProblemTagDTO;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProblemListElementDTO {
    private Long id;
    private String title;
    private Integer level;
    private List<ProblemTagDTO> tags;
}
