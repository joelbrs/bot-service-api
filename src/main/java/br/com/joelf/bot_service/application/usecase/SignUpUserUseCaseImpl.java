package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.application.dataprovider.UserDataProvider;
import br.com.joelf.bot_service.domain.dtos.user.SignUpUserDto;
import br.com.joelf.bot_service.domain.dtos.user.UserDtoOut;
import br.com.joelf.bot_service.domain.entities.Organization;
import br.com.joelf.bot_service.domain.usecase.SignUpUserUseCase;
import br.com.joelf.bot_service.domain.usecase.exceptions.SignUpUserUseCaseException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

@AllArgsConstructor
public class SignUpUserUseCaseImpl implements SignUpUserUseCase {

    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final UserDataProvider userDataProvider;

    @Override
    public UserDtoOut execute(SignUpUserDto user) {
        if (!user.getOrganizationIdentifier().equals(Organization.CNPJ)) {
            throw new SignUpUserUseCaseException("Invalid organization identifier", HttpStatus.BAD_REQUEST);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return modelMapper.map(userDataProvider.signUp(user), UserDtoOut.class);
    }
}
