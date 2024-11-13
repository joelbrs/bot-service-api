package br.com.joelf.bot_service.application.usecase;

import br.com.joelf.bot_service.domain.dtos.user.UserDtoOut;
import br.com.joelf.bot_service.domain.usecase.FindLoggedUserUseCase;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@AllArgsConstructor
public class FindLoggedUserUseCaseImpl implements FindLoggedUserUseCase {

    private final ModelMapper modelMapper;

    @Override
    public UserDtoOut execute() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return modelMapper.map(authentication.getPrincipal(), UserDtoOut.class);
    }
}
