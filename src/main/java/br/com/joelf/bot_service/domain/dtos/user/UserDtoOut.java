package br.com.joelf.bot_service.domain.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDtoOut {
    private UUID id;
    private String cpfCnpj;
    private String name;
}
