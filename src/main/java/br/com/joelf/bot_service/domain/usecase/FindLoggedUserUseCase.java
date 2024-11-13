package br.com.joelf.bot_service.domain.usecase;

import br.com.joelf.bot_service.domain.dtos.user.UserDtoOut;

public interface FindLoggedUserUseCase {
    UserDtoOut execute();
}
