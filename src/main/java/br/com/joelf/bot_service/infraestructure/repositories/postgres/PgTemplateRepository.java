package br.com.joelf.bot_service.infraestructure.repositories.postgres;

import br.com.joelf.bot_service.infraestructure.repositories.postgres.domain.PgTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PgTemplateRepository extends JpaRepository<PgTemplate, UUID> {

    @Query("SELECT t FROM PgTemplate t " +
            "WHERE t.name IS NULL OR t.name LIKE CONCAT('%', CAST(:name AS string), '%') " +
            "ORDER BY t.name"
    )
    Page<PgTemplate> findAllPaged(Pageable pageable, String name);
}
