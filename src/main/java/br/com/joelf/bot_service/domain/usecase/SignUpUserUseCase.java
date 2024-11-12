package br.com.joelf.bot_service.domain.usecase;

import br.com.joelf.bot_service.domain.dtos.user.SignUpUserDto;
import br.com.joelf.bot_service.domain.entities.User;

public interface SignUpUserUseCase {
    User execute(SignUpUserDto user);
}
