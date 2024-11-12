package br.com.joelf.bot_service.domain.dtos.user;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDtoOut {
    private UUID id;
    private String cpfCnpj;
    private String name;
}
