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

    @Query(value = "SELECT * FROM tb_product p " +
            "WHERE p.status = 'DISPONIVEL' " +
            "AND EXISTS ( " +
            "   SELECT 1 FROM unnest(:names) AS name " +
            "   WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', name, '%')) " +
            ") " +
            "ORDER BY p.name", nativeQuery = true)
    List<PgProduct> findByName(String[] names);
}
