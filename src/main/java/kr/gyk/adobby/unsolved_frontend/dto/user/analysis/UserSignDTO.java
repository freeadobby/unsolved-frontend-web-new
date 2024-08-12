package kr.gyk.adobby.unsolved_frontend.dto.user.analysis;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserSignDTO {
    private Long id;
    private String email;
    private String username;
    private String password;
    private String baekjoon;
}
