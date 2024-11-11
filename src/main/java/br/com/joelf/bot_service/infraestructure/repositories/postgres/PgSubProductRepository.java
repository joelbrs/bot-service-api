package br.com.joelf.bot_service.infraestructure.repositories.postgres;

import br.com.joelf.bot_service.infraestructure.repositories.postgres.domain.PgSubProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PgSubProductRepository extends JpaRepository<PgSubProduct, UUID> {

    @Modifying
    @Query("DELETE FROM PgSubProduct p WHERE p.product.id = :id")
    void deleteAllByProductId(UUID id);

    @Query("SELECT p FROM PgSubProduct p WHERE p.product.id = :id")
    List<PgSubProduct> findAllByProductId(UUID id);
}
