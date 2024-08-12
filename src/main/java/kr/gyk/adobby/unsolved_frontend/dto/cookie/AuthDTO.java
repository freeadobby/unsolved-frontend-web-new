package kr.gyk.adobby.unsolved_frontend.dto.cookie;

import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {
    private String accessToken;
    private String refreshToken;
    private String userEmail;
}
