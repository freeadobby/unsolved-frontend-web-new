package kr.gyk.adobby.unsolved_frontend.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {
    @NotEmpty(message = "이메일은 필수항목입니다")
    @Size(max = 128)
    private String email;

    @NotEmpty(message = "비밀번호는 필수항목입니다")
    @Size(max = 256)
    private String password;
}
