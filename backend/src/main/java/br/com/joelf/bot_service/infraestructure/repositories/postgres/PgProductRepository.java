package br.com.joelf.bot_service.infraestructure.repositories.postgres;

import br.com.joelf.bot_service.infraestructure.repositories.postgres.domain.PgProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PgProductRepository extends JpaRepository<PgProduct, UUID> {
}
