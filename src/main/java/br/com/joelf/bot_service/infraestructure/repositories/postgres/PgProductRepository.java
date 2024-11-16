package br.com.joelf.bot_service.infraestructure.repositories.postgres;

import br.com.joelf.bot_service.domain.entities.ProductStatus;
import br.com.joelf.bot_service.infraestructure.repositories.postgres.domain.PgProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PgProductRepository extends JpaRepository<PgProduct, UUID> {

    @Query("SELECT p FROM PgProduct p " +
            "WHERE (:name IS NULL OR p.name LIKE CONCAT('%', CAST(:name AS string), '%')) " +
            "AND (:status IS NULL OR p.status = :status) " +
            "ORDER BY p.name"
    )
    Page<PgProduct> findAllPaged(Pageable pageable, String name, ProductStatus status);

    @Query("SELECT p FROM PgProduct p " +
            "WHERE (LOWER(p.name) IN :names) " +
            "AND p.status = 'DISPONIVEL' " +
            "ORDER BY p.name")
    List<PgProduct> findByName(@Param("names") List<String> names);
}
