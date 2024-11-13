package br.com.joelf.bot_service.application.dataprovider;

import br.com.joelf.bot_service.domain.dtos.user.SignUpUserDto;
import br.com.joelf.bot_service.domain.entities.User;

public interface UserDataProvider {
    User signUp(SignUpUserDto user);
    User findByCpfCnpj(String cpfCnpj);
}
