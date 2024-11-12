package br.com.joelf.bot_service.domain.dtos.user;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignInUserDto {

    @NotBlank
    private String cpfCnpj;

    @NotBlank
    @Min(8)
    private String password;
}
