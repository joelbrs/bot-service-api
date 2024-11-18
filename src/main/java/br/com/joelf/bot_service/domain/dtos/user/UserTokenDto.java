package br.com.joelf.bot_service.domain.dtos.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserTokenDto {
    private String token;
    private Long expirationTime;
}
