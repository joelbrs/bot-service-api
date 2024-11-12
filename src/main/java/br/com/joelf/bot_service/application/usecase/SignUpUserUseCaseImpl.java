package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.UserDataProvider;
import br.com.joelf.bot_service.domain.dtos.user.SignUpUserDto;
import br.com.joelf.bot_service.domain.dtos.user.UserDtoOut;
import br.com.joelf.bot_service.domain.usecase.SignUpUserUseCase;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class SignUpUserUseCaseImpl implements SignUpUserUseCase {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDataProvider userDataProvider;

    @Override
    public UserDtoOut execute(SignUpUserDto user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return modelMapper.map(userDataProvider.signUp(user), UserDtoOut.class);
    }
}
