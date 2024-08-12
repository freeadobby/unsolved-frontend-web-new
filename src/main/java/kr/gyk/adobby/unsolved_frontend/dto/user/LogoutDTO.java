package kr.gyk.adobby.unsolved_frontend.dto.user;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LogoutDTO {
    private String email;
    private String accessToken;
}
