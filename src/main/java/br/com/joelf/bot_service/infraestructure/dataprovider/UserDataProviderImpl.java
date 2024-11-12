package br.com.joelf.bot_service.infraestructure.dataprovider;

import br.com.joelf.bot_service.application.dataprovider.UserDataProvider;
import br.com.joelf.bot_service.application.dataprovider.exceptions.UserDataProviderException;
import br.com.joelf.bot_service.domain.dtos.user.SignUpUserDto;
import br.com.joelf.bot_service.domain.entities.User;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.PgUserRepository;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.domain.PgUser;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
public class UserDataProviderImpl implements UserDataProvider {

    private final ModelMapper modelMapper;
    private final PgUserRepository pgUserRepository;

    @Override
    public User signUp(SignUpUserDto user) {
        PgUser pgUser = modelMapper.map(user, PgUser.class);
        return modelMapper.map(pgUserRepository.save(pgUser), User.class);
    }

    @Override
    public User findByCpfCnpj(String cpfCnpj) {
        PgUser pgUser = pgUserRepository.findByCpfCnpj(cpfCnpj)
                .orElseThrow(() -> new UserDataProviderException("User not found, cpfCnpj: " + cpfCnpj));
        return modelMapper.map(pgUser, User.class);
    }
}
