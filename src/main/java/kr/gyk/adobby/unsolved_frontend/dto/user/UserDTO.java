package kr.gyk.adobby.unsolved_frontend.dto.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private String baekjoonID;
    private List<AuthorityDTO> roles = new ArrayList<>();
    private TokenDTO token;
}
