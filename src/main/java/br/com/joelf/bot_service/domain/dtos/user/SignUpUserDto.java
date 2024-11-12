package br.com.joelf.bot_service.domain.dtos.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignUpUserDto {

    @NotBlank
    @Size(min = 11, max = 14)
    private String cpfCnpj;

    @NotBlank
    private String name;

    @NotBlank
    @Min(8)
    private String password;

    @NotBlank
    @Min(8)
    private String passwordConfirmation;

    public SignUpUserDto(String cpfCnpj, String name, String password, String passwordConfirmation) {
        this.cpfCnpj = cpfCnpj;
        this.name = name;
        this.password = password;
        this.passwordConfirmation = passwordConfirmation;
        validate();
    }

    private void validate() {
        if (!password.equals(passwordConfirmation)) {
            throw new IllegalArgumentException("Password and password confirmation must be the same");
        }
    }
}
