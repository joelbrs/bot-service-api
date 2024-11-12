package br.com.joelf.bot_service.infraestructure.repositories.postgres;

import br.com.joelf.bot_service.infraestructure.repositories.postgres.domain.PgUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PgUserRepository extends JpaRepository<UUID, PgUser> {

    @Query("SELECT u FROM PgUser u WHERE u.cpfCnpj = :cpfCnpj")
    Optional<PgUser> findByCpfCnpj(String cpfCnpj);
}
