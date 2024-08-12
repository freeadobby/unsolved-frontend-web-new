package kr.gyk.adobby.unsolved_frontend.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterForm {
    @NotEmpty(message = "이메일은 필수항목입니다")
    @Size(max = 128)
    private String email;

    @NotEmpty(message = "유저이름은 필수항목입니다")
    @Size(max = 32)
    private String username;

    @NotEmpty(message = "비밀번호는 필수항목입니다")
    @Size(max = 256)
    private String password;

    @NotEmpty(message = "백준 아이디는 필수항목입니다")
    @Size(max = 256)
    private String baekjoon;
}