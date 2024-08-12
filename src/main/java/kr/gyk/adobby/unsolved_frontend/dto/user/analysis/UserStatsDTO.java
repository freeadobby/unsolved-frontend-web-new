package kr.gyk.adobby.unsolved_frontend.dto.user.analysis;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@RequiredArgsConstructor
public class UserStatsDTO {
    private String username;

    private ArrayList<Long> problemSolved;

    private Integer sRanking;
    private Integer sProblemSolved;
    private Integer sProblemSolvedNotPerfect;
    private Integer sProblemWrong;
    private Integer sCountSubmit;
    private Integer sCountRight;
    private Integer sCountWrong;
    private Integer sCountOutputFormat;
    private Integer sCountTimeout;
    private Integer sCountMemoryEx;
    private Integer sCountOutputEx;
    private Integer sCountRuntimeErr;
    private Integer sCountCompileRrr;

    private String rating;
    private Integer ratingScore;
}
